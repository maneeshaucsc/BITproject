package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Reading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface ReadingDao extends JpaRepository<Reading,Integer> {

    @Query(value="SELECT new Reading (r.id,r.time) FROM Reading r")
    List<Reading> list();

    @Query("SELECT r FROM Reading r ORDER BY r.id DESC")
    Page<Reading> findAll(Pageable of);

    @Query("SELECT r FROM Reading r WHERE r.time= :time")
    Reading findByReadingtime(@Param("time") LocalTime time);
}
