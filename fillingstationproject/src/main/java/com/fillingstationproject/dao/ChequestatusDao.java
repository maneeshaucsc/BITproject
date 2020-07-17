package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Chequestatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChequestatusDao extends JpaRepository<Chequestatus,Integer> {

    @Query(value="SELECT new Chequestatus (c.id,c.status) FROM Chequestatus c")
    List<Chequestatus> list();

    @Query("SELECT c FROM Chequestatus c ORDER BY c.id DESC")
    Page<Chequestatus> findAll(Pageable of);

}
