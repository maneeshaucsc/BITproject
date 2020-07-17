package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface MeasurementDao extends JpaRepository<Measurement,Integer> {

    @Query(value="SELECT new Measurement (m.id,m.measuredtime) FROM Measurement m")
    List<Measurement> list();

    @Query("SELECT m FROM Measurement m ORDER BY m.id DESC")
    Page<Measurement> findAll(Pageable of);

    @Query("SELECT m FROM Measurement m WHERE m.measuredtime= :measuredtime")
    Measurement findByMeasuretime(@Param("measuredtime") LocalTime measuredtime);
}
