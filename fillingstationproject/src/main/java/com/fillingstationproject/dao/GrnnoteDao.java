package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Grnnote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrnnoteDao extends JpaRepository<Grnnote,Integer> {

    @Query(value="SELECT new Grnnote (g.id,g.grnno,g.totalamount) FROM Grnnote g")
    List<Grnnote> list();


    @Query("SELECT g FROM Grnnote g ORDER BY g.id DESC")
    Page<Grnnote> findAll(Pageable of);

    @Query(value = "SELECT concat('G',lpad(substring(max(g.grnno),2,4)+1,4,'0'))FROM Grnnote g", nativeQuery=true)
    String lastGrnno();

    @Query("SELECT g FROM Grnnote g WHERE g.grnno= :grnno")
    Grnnote findByGrnNo(@Param("grnno")String grnno);




}
