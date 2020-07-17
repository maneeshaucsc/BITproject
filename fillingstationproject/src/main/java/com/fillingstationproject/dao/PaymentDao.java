package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment,Integer> {

    @Query(value="SELECT new Payment (p.id,p.payno) FROM Payment p")
    List<Payment> list();

    @Query("SELECT p FROM Payment p ORDER BY p.id DESC")
    Page<Payment> findAll(Pageable of);

    @Query(value = "SELECT concat('S',lpad(substring(max(p.payno),2,4)+1,4,'0'))FROM Payment p", nativeQuery=true)
    String lastPaymentno();

    @Query("SELECT p FROM Payment p WHERE p.payno= :payno")
    Payment findByPayNo(@Param("payno")String payno);

}
