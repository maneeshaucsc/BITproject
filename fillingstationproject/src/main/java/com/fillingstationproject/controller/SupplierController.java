package com.fillingstationproject.controller;

import com.fillingstationproject.dao.ItemtypeDao;
import com.fillingstationproject.dao.SupplierDao;
import com.fillingstationproject.dao.SupplierstatusDao;
import com.fillingstationproject.entity.Supplier;
import com.fillingstationproject.entity.Supply;
import com.fillingstationproject.services.EmailService;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class SupplierController {

    @Autowired
    private SupplierDao dao;

    @Autowired
    private ItemtypeDao daoItemtype;

    @Autowired
    private SupplierstatusDao daoSupplierstatus;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/suppliers/list", method = RequestMethod.GET, produces = "application/json")
    public List<Supplier> list(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isUser(username,password)) {
            return dao.list();
        }
        return null;
    }

    @RequestMapping(value = "/suppliers/regno", method = RequestMethod.GET, produces = "application/json")
    public String lastRegno(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.SUPPLIER,AuthProvider.SELECT)) {
            String regno = dao.lastRegno();
            // Integer porderNumber = Integer.parseInt(pono)+1;

            return "{\"no\":\""+regno+"\" }";//json format eken thyenn one nisa
        }
        return null;
    }

   /* @RequestMapping(value = "/suppliers/listbyitype",params ="brandId",method = RequestMethod.GET, produces = "application/json")
    public List<Supplier> categories(@Param("itemtypeId") Integer brandId) {
        return dao.listByBrand(brandId);
    }*/

    @RequestMapping(value = "/suppliers", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Supplier> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.SUPPLIER,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/suppliers", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Supplier supplier) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.EMPLOYEE,AuthProvider.INSERT)) {
            Supplier supname = dao.findBySupname(supplier.getCompany());
            //Supplier connum = dao.findByConnumber(supplier.getContactno());
            if (supname != null)
                return "Error-Validation : Company Exists";
            else
                try {
                    String message = "Dear "+supplier.getCompany()+"\n\n" +
                            "Dissanayake Filling Station gives you the the best fuel filling service with the best quality of service...";

                    emailService.sendSupplierEmail("maneeshasandagomi@gmail.com","Welcome to Dissanayake Filling Station",message);

                    for (Supply supply: supplier.getSupplyList()){
                        supply.setSupplierId(supplier);
                    }

                    dao.save(supplier);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/suppliers", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Supplier supplier) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.SUPPLIER,AuthProvider.UPDATE)) {
            Supplier sup = dao.findBySupname(supplier.getCompany());
            if(sup==null || sup.getId()==supplier.getId()) {
                try {
                    dao.save(supplier);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Supplier Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/suppliers", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Supplier supplier ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.SUPPLIER,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(supplier.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/suppliers", params = {"page", "size","company","itemtypeid","statusid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Supplier> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("company") String company, @RequestParam("itemtypeid") Integer itemtypeid, @RequestParam("statusid") Integer supplierstatusid) {

        // System.out.println(name+"-"+nic+"-"+designationid+"-"+employeestatusid);


        if(AuthProvider.isAuthorized(username,password, ModuleList.SUPPLIER,AuthProvider.SELECT)) {

            List<Supplier> suppliers = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Supplier> supplierstream = suppliers.stream();
            //adminwa search nowenn  object ekkt nm equals,
           // supplierstream = supplierstream.filter(e -> !(e.getCallingname().equals("Admin")));

            if (itemtypeid != null)
                supplierstream = supplierstream.filter(s -> s.getSupplyList().equals(daoItemtype.getOne(itemtypeid)));
            if (supplierstatusid != null)
                supplierstream = supplierstream.filter(s -> s.getSupplierstatusId().equals(daoSupplierstatus.getOne(supplierstatusid)));
            supplierstream = supplierstream.filter(s -> s.getCompany().contains(company));


            List<Supplier> suppliers2 = supplierstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < suppliers2.size() ? start + size : suppliers2.size();
            Page<Supplier> suppage = new PageImpl<>(suppliers2.subList(start, end), PageRequest.of(page, size), suppliers2.size());

            return suppage;
        }

        return null;

    }




}
