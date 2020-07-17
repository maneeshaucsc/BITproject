package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

    @Query("SELECT c FROM Customer c ORDER BY c.id DESC")
    Page<Customer> findAll(Pageable of);

    @Query(value="SELECT new Customer(c.id,c.name) FROM Customer c")
    List<Customer> list();

    @Query("SELECT c FROM Customer c WHERE c.name= :name")
    Customer findByName(@Param("name")String name);
}