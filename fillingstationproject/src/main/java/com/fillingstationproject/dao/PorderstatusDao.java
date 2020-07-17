package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Porderstatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PorderstatusDao extends JpaRepository<Porderstatus,Integer> {

    @Query(value="SELECT new Porderstatus  (p.id,p.status) FROM Porderstatus p")
    List<Porderstatus> list();

    @Query("SELECT p FROM Porderstatus p ORDER BY p.id DESC")
    Page<Porderstatus> findAll(Pageable of);
}
