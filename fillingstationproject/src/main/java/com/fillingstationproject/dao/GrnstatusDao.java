package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Grnstatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrnstatusDao extends JpaRepository<Grnstatus,Integer> {

    @Query(value="SELECT new Grnstatus (gs.id,gs.status) FROM Grnstatus gs")
    List<Grnstatus> list();

    @Query("SELECT gs FROM Grnstatus gs ORDER BY gs.id DESC")
    Page<Grnstatus> findAll(Pageable of);

}
