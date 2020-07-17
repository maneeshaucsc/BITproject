package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Invoicestatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoicestatusDao extends JpaRepository<Invoicestatus,Integer> {

    @Query(value="SELECT new Invoicestatus (ios.id,ios.status) FROM Invoicestatus ios")
    List<Invoicestatus> list();

    @Query("SELECT ios FROM Invoicestatus ios ORDER BY ios.id DESC")
    Page<Invoicestatus> findAll(Pageable of);
}
