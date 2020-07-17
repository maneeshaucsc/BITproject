package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankDao extends JpaRepository<Bank,Integer> {

    @Query(value="SELECT new Bank (b.id,b.name) FROM Bank b ")
    List<Bank> list();

    @Query("SELECT b FROM Bank b ORDER BY b.id DESC")
    Page<Bank> findAll(Pageable of);
}
