package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Customerorderstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerorderstatusDao extends JpaRepository<Customerorderstatus, Integer> {

    @Query(value="SELECT new Customerorderstatus (cos.id,cos.status) FROM Customerorderstatus cos")
    List<Customerorderstatus> list();
}
