package com.fillingstationproject.controller;

import com.fillingstationproject.dao.CustomerDao;
import com.fillingstationproject.dao.CustomerstatusDao;
import com.fillingstationproject.entity.Customer;
import com.fillingstationproject.services.EmailService;
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
public class CustomerController {

    @Autowired
    private CustomerDao dao;

    @Autowired
    private CustomerstatusDao daocustomerstatus;

    @Autowired
    private EmailService emailService;


    @RequestMapping(value = "/customers", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Customer> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.CUSTOMER,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/customers/list", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> gender() {
        return dao.list();
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Customer customer) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.CUSTOMER, AuthProvider.INSERT)) {
            Customer cuscode = dao.findByName(customer.getName());
            if (cuscode != null)
                return "Error-Validation : Customer Exists";
            else
                try {
                    String message = "Dear "+customer.getName()+"\n\n" +
                            "Dissanayake Filling Station gives you the the best fuel filling service with the best quality of service...";

                    emailService.sendSupplierEmail("maneeshasandagomi@gmail.com","Welcome to Dissanayake Filling Station",message);


                    dao.save(customer);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Customer customer) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CUSTOMER, AuthProvider.UPDATE)) {

            try {
                dao.save(customer);
                return "0";
            } catch (Exception e) {
                return "Error-Updating : " + e.getMessage();
            }
        }
        return "Error-Updating : You have no Permission";
        //return "0";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Customer customer) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CUSTOMER, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(customer.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }
    @RequestMapping(value = "/customers", params = {"page", "size", "name","customerstatusid",}, method = RequestMethod.GET, produces = "application/json")
    public Page<Customer> findAll(@CookieValue(value = "username") String username,
                              @CookieValue(value = "password") String password,
                              @RequestParam("page") int page,
                              @RequestParam("size") int size,
                              @RequestParam("name") String name,
                              @RequestParam("customerstatusid") Integer customerstatusid) {

        //System.out.println(brand + "-" + itemtypeid);

        //checking the authentication
        if (AuthProvider.isAuthorized(username, password, ModuleList.CUSTOMER, AuthProvider.SELECT)) {

            //bring all the data from the database
            List<Customer> customers = dao.findAll(Sort.by(Sort.Direction.DESC,"id"));

            //converting the customer list to a stream to filter them
            Stream<Customer> customerstream = customers.stream();

            customerstream = customerstream.filter(c -> c.getName().contains(name));

            if (customerstatusid != null)
                customerstream = customerstream.filter(f -> f.getCustomerstatusId().equals(daocustomerstatus.getOne(customerstatusid)));


            List<Customer> customer2 = customerstream.collect(Collectors.toList());

            //so after filtered it converts to a page
            int start = page * size;
            int end = start + size < customer2.size() ? start + size : customer2.size();
            Page<Customer> cstmpage = new PageImpl<>(customer2.subList(start, end), PageRequest.of(page, size), customer2.size());

            return cstmpage;
        }

        return null;

    }

}
