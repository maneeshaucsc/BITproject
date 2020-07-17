package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {

    @Query(value = "SELECT concat('I',lpad(substring(max(i.invoiceno),2,5)+1,5,'0'))FROM Invoice i", nativeQuery=true)
    String lastInvoiceno();

    @Query(value="SELECT new Invoice (i.id,i.invoiceno) FROM Invoice i")
    List<Invoice> list();

    @Query("SELECT i FROM Invoice i ORDER BY i.id DESC")
    Page<Invoice> findAll(Pageable of);

    @Query("SELECT i FROM Invoice i WHERE i.invoiceno= :invoiceno")
    Invoice findByInvoiceNo(@Param("invoiceno")String invoiceno);


}
