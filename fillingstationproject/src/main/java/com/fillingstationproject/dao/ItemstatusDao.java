package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Itemstatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemstatusDao extends JpaRepository<Itemstatus,Integer> {
    @Query(value="SELECT new Itemstatus (i.id,i.status) FROM Itemstatus i")
    List<Itemstatus> list();

    @Query("SELECT i FROM Itemstatus i ORDER BY i.id DESC")
    Page<Itemstatus> findAll(Pageable of);

    @Query("SELECT i FROM Itemstatus i WHERE i.status= :status")
    Itemstatus findByStatus(@Param("status")String status);
}
