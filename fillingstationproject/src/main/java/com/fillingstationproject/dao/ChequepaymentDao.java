package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Chequepayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChequepaymentDao extends JpaRepository<Chequepayment,Integer> {

    @Query(value="SELECT new Chequepayment (c.id,c.cno) FROM Chequepayment c ")
    List<Chequepayment> list();

    @Query("SELECT c FROM Chequepayment c ORDER BY c.id DESC")
    Page<Chequepayment> findAll(Pageable of);

    @Query(value = "SELECT concat('CH',lpad(substring(max(cp.cno),2,5)+1,5,'0'))FROM Chequepayment cp", nativeQuery=true)
    String lastChequeno();

    @Query("SELECT p FROM Chequepayment p WHERE p.cno= :cno")
    Chequepayment findByChNo(@Param("cno")String cno);
}
