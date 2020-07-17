package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Meter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeterDao extends JpaRepository<Meter,Integer> {
    @Query(value="SELECT new Meter (m.id,m.code) FROM Meter m")
    List<Meter> list();

    @Query("SELECT m FROM Meter m ORDER BY m.id DESC")
    Page<Meter> findAll(Pageable of);

    @Query("SELECT m FROM Meter m WHERE m.code= :code")
    Meter findByCode(@Param("code")String code);
}
