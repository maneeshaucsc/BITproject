package com.fillingstationproject.controller;


import com.fillingstationproject.dao.*;
import com.fillingstationproject.entity.*;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class GrnnoteController {

    @Autowired
    private GrnnoteDao dao;

    @Autowired
    private GrnstatusDao daogrnstatus;

    @Autowired
    private PorderDao daoporder;

    @Autowired
    private PorderstatusDao daoporderstatus;

    @Autowired
    private ItemDao daoitem;

    @RequestMapping(value = "/grns/list",method = RequestMethod.GET, produces = "application/json")
    public List<Grnnote> list()  {
        return dao.list();
    }


    @RequestMapping(value = "/grns", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Grnnote> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.GRNNOTE,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/grns/grnno", method = RequestMethod.GET, produces = "application/json")
    public String lastGrnno(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.GRNNOTE,AuthProvider.SELECT)) {
            String grnno = dao.lastGrnno();
            // Integer porderNumber = Integer.parseInt(pono)+1;

            return "{\"no\":\""+grnno+"\" }";//json format eken thyenn one nisa
        }
        return null;
    }

    @Transactional
    @RequestMapping(value = "/grns", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Grnnote grnnote) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.GRNNOTE,AuthProvider.INSERT)) {
            Grnnote grn = dao.findByGrnNo(grnnote.getGrnno());

            if (grn != null)
                return "Error-Validation : Porder No Exists";

                try {

                    for (Grnnoteitem grnitm : grnnote.getGrnnoteitemList()) {
                        grnitm.setGrnnoteId(grnnote);

                        Item item = daoitem.getOne(grnitm.getItemId().getId());
                        item.setQuantity(item.getQuantity() + grnitm.getQuantity());
                        daoitem.save(item);
                    }

                    System.out.println(grnnote.getPorderId().getPono());

                        Porder porder = daoporder.getOne(grnnote.getPorderId().getId());
                        Porderstatus porderstatus = new Porderstatus(2);
                        porder.setPorderstatusId(porderstatus);
                        daoporder.save(porder);

                        /*Porder porderid = grn.getPorderId(); //grn eke add ekak krddi etkta adala purchase order ekata status eka maru wenwa delieved kiyla
                        Porderstatus posta = daoporderstatus.getOne(2);
                        Porder prdr = daoporder.getOne(porderid.getId());
                        porderid.setPorderstatusId(posta);
                        daoporder.save(prdr);*/


                        dao.save(grnnote);
                        return "0";

                }catch(Exception e){
                    e.printStackTrace();
                        return "Error-Saving : " + e.getMessage();
                    }
                }

        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/grns", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Grnnote grnnote) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.GRNNOTE,AuthProvider.UPDATE)) {

            try {

                for (Grnnoteitem grn : grnnote.getGrnnoteitemList())
                    grn.setGrnnoteId(grnnote);


                dao.save(grnnote);
                return "0";
            }
            catch(Exception e) {
                return "Error-Updating : "+e.getMessage();
            }
        }
        else {  return "Error-Updating : Reg No Exists"; }

    }

    @RequestMapping(value = "/grns", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Grnnote grnnote ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.GRNNOTE,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(grnnote.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/grns", params = {"page", "size","grnno","statusid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Grnnote> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password,
                                @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("grnno") String grnno, @RequestParam("statusid") Integer statusid) {

        if(AuthProvider.isAuthorized(username,password, ModuleList.GRNNOTE,AuthProvider.SELECT)) {

            List<Grnnote> grnnotes = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Grnnote> grnnotestream = grnnotes.stream();
            //adminwa search nowenn  object ekkt nm equals,
            // supplierstream = supplierstream.filter(e -> !(e.getCallingname().equals("Admin")));

            if (statusid != null)
                grnnotestream = grnnotestream.filter(g -> g.getGrnstatusId().equals(daogrnstatus.getOne(statusid)));
            grnnotestream = grnnotestream.filter(g -> g.getGrnno().contains(grnno));


            List<Grnnote> grnnotes2 = grnnotestream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < grnnotes2.size() ? start + size : grnnotes2.size();
            Page<Grnnote> grnpage = new PageImpl<>(grnnotes2.subList(start, end), PageRequest.of(page, size), grnnotes2.size());

            return grnpage;
        }

        return null;

    }




}
