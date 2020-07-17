package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Porder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PorderDao extends JpaRepository<Porder, Integer> {

    @Query("SELECT p FROM Porder p ORDER BY p.id DESC")
    Page<Porder> findAll(Pageable of);

    @Query(value="SELECT new Porder (p.id,p.pono) FROM Porder p")
    List<Porder> list();

    @Query("SELECT p FROM Porder p WHERE p.pono= :pono")
    Porder findByPoNo(@Param("pono")String porderno);

    @Query(value = "SELECT concat('P',lpad(substring(max(po.pono),2,5)+1,5,'0'))FROM Porder po", nativeQuery=true)
    String lastPorderno();

    @Query(value="SELECT new Porder(p.id,p.pono) FROM Porder p where p.supplierId.id =:supplierId")
    List<Porder> porderbysup(@Param("supplierId") Integer supplierId );

    //    @Query("SELECT SUBSTRING(MAX(co.orderno),2,5) FROM Corder co")
//    String lastOrderno();
}
