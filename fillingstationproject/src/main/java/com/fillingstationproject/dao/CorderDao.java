package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Corder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CorderDao extends JpaRepository<Corder,Integer> {

    @Query("SELECT co FROM Corder co ORDER BY co.id DESC")
    Page<Corder> findAll(Pageable of);

    @Query(value="SELECT new Corder (co.id,co.orderno) FROM Corder co")
    List<Corder> list();

    @Query(value = "SELECT concat('C',lpad(substring(max(co.orderno),2,4)+1,4,'0'))FROM Corder co", nativeQuery=true)
    String lastCorderno();

    @Query("SELECT co FROM Corder co WHERE co.orderno= :orderno")
    Corder findByOrderno(@Param("orderno")String orderno);

    @Query(value="SELECT new Corder (c.id,c.orderno) FROM Corder c where c.customerId.id =:customerId")
    List<Corder> ordernobycus(@Param("customerId") Integer customerId );

//    @Query("SELECT co FROM Corder co WHERE co.customerId= :customerId")
//    List<Corder> listbycustomer(@Param("customerId")Integer customerId);


}
