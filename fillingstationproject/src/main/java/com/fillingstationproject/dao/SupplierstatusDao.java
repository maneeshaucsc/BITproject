package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Supplierstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierstatusDao extends JpaRepository<Supplierstatus,Integer> {
    @Query(value="SELECT new Supplierstatus  (s.id,s.status) FROM Supplierstatus s")
    List<Supplierstatus> list();
}
