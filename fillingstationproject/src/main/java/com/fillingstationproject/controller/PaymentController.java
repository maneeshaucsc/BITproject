package com.fillingstationproject.controller;

import com.fillingstationproject.dao.PaymentDao;
import com.fillingstationproject.dao.PaymenttypeDao;
import com.fillingstationproject.entity.Payment;
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
public class PaymentController {

    @Autowired
    private PaymentDao dao;

    @Autowired
    private PaymenttypeDao daopaymenttype;

    @RequestMapping(value = "/payments/list", method = RequestMethod.GET, produces = "application/json")
    public List<Payment> payments() {
        return dao.list();
    }

    @RequestMapping(value = "/payments", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Payment> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.PAYMENT,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/payments/payno", method = RequestMethod.GET, produces = "application/json")
    public String lastPaymentno(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.PAYMENT,AuthProvider.SELECT)) {
            String payno = dao.lastPaymentno();
            // Integer porderNumber = Integer.parseInt(pono)+1;

            return "{\"no\":\""+payno+"\" }";//json format eken thyenn one nisa
        }
        return null;
    }

    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Payment payment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.PAYMENT, AuthProvider.INSERT)) {
            Payment payno = dao.findByPayNo(payment.getPayno());
            if (payno != null)
                return "Error-Validation : Payment no Exists";
            else if (payno != null)
                return "Error-Validation : Payment no Exists";
            else
                try {
                    dao.save(payment);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/payments", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Payment payment) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.PAYMENT, AuthProvider.UPDATE)) {

            try {
                dao.save(payment);
                return "0";
            } catch (Exception e) {
                return "Error-Updating : " + e.getMessage();
            }
        }
        return "Error-Updating : You have no Permission";
        //return "0";
    }

    @RequestMapping(value = "/payments", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Payment payment) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.PAYMENT, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(payment.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/payments", params = {"page", "size", "payno","paymenttypeid",}, method = RequestMethod.GET, produces = "application/json")
    public Page<Payment> findAll(@CookieValue(value = "username") String username,
                                 @CookieValue(value = "password") String password,
                                 @RequestParam("page") int page,
                                 @RequestParam("size") int size,
                                 @RequestParam("payno") String payno,
                                 @RequestParam("paymenttypeid") Integer paymenttypeid) {


        if (AuthProvider.isAuthorized(username, password, ModuleList.PAYMENT, AuthProvider.SELECT)) {

            //bring all the data from the database
            List<Payment> payments = dao.findAll(Sort.by(Sort.Direction.DESC,"id"));

            //converting the item list to a stream to filter them
            Stream<Payment> paymentstream = payments.stream();

            if (paymenttypeid != null)
                paymentstream = paymentstream.filter(i -> i.getId().equals(daopaymenttype.getOne(paymenttypeid)));

            paymentstream = paymentstream.filter(i -> i.getPayno().startsWith(payno));

            List<Payment> payment2 = paymentstream.collect(Collectors.toList());

            //so after filtered it converts to a page
            int start = page * size;
            int end = start + size < payment2.size() ? start + size : payment2.size();
            Page<Payment> paypage = new PageImpl<>(payment2.subList(start, end), PageRequest.of(page, size), payment2.size());

            return paypage;
        }

        return null;

    }


}
