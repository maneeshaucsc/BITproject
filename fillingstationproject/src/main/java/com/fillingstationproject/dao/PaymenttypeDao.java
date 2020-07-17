package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Paymenttype;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymenttypeDao extends JpaRepository<Paymenttype,Integer> {

    @Query(value="SELECT new Paymenttype (pt.id,pt.type) FROM Paymenttype pt")
    List<Paymenttype> list();

    @Query("SELECT pt FROM Paymenttype pt ORDER BY pt.id DESC")
    Page<Paymenttype> findAll(Pageable of);

}
