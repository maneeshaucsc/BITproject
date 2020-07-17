package com.fillingstationproject.controller;

import com.fillingstationproject.dao.InvoiceDao;
import com.fillingstationproject.dao.InvoicestatusDao;
import com.fillingstationproject.dao.ItemDao;
import com.fillingstationproject.entity.Invoice;
import com.fillingstationproject.entity.Invoiceitem;
import com.fillingstationproject.entity.Invoicestatus;
import com.fillingstationproject.entity.Item;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceDao dao;

    @Autowired
    private InvoicestatusDao daoinvoicestatus;

    @Autowired
    private ItemDao daoitem;

    @RequestMapping(value = "/invoices/list", method = RequestMethod.GET, produces = "application/json")
    public List<Invoice> banks() {
        return dao.list();
    }

    @RequestMapping(value = "/invoices", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Invoice> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.INVOICE,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/invoices/invoiceno", method = RequestMethod.GET, produces = "application/json")
    public String lastInvoiceno(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.INVOICE,AuthProvider.SELECT)) {
            String invoiceno = dao.lastInvoiceno();
            // Integer porderNumber = Integer.parseInt(pono)+1;

            return "{\"no\":\""+invoiceno+"\" }";//json format eken thyenn one nisa
        }
        return null;
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Invoice invoice) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.INVOICE, AuthProvider.INSERT)) {
            Invoice invoiceNo = dao.findByInvoiceNo(invoice.getInvoiceno());
            if (invoiceNo != null)
                return "Error-Validation : Invoice no Exists";
            else
                try {
                   for (Invoiceitem invitm : invoice.getInvoiceitemList()) {
                       invitm.setInvoiceId(invoice);
                       Item item = daoitem.getOne(invitm.getItemId().getId());
                       item.setQuantity(item.getQuantity() - invitm.getQuantity());
                       daoitem.save(item);

                       dao.save(invoice);
                       return "0";
                   }
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/invoices", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Invoice invoice) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.INVOICE, AuthProvider.UPDATE)) {

            try {
                dao.save(invoice);
                return "0";
            } catch (Exception e) {
                return "Error-Updating : " + e.getMessage();
            }
        }
        return "Error-Updating : You have no Permission";
        //return "0";
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Invoice invoice) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.INVOICE, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(invoice.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/invoices", params = {"page", "size", "invoiceno","statusid",}, method = RequestMethod.GET, produces = "application/json")
    public Page<Invoice> findAll(@CookieValue(value = "username") String username,
                              @CookieValue(value = "password") String password,
                              @RequestParam("page") int page,
                              @RequestParam("size") int size,
                              @RequestParam("invoiceno") String invoiceno,
                              @RequestParam("statusid") Integer statusid) {

        //System.out.println(brand + "-" + itemtypeid);

        //checking the authentication
        if (AuthProvider.isAuthorized(username, password, ModuleList.INVOICE, AuthProvider.SELECT)) {

            //bring all the data from the database
            List<Invoice> invoices = dao.findAll(Sort.by(Sort.Direction.DESC,"id"));

            //converting the item list to a stream to filter them
            Stream<Invoice> invoicestream = invoices.stream();

            if (statusid != null)
                invoicestream = invoicestream.filter(i -> i.getId().equals(daoinvoicestatus.getOne(statusid)));

            invoicestream = invoicestream.filter(i -> i.getInvoiceno().startsWith(invoiceno));

            List<Invoice> invoice2 = invoicestream.collect(Collectors.toList());

            //so after filtered it converts to a page
            int start = page * size;
            int end = start + size < invoice2.size() ? start + size : invoice2.size();
            Page<Invoice> inpage = new PageImpl<>(invoice2.subList(start, end), PageRequest.of(page, size), invoice2.size());

            return inpage;
        }

        return null;

    }
}
