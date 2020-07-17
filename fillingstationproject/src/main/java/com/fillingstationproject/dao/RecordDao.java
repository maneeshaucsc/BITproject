package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordDao extends JpaRepository<Record,Integer> {

    @Query(value="SELECT new Record (r.id,r.pumpedtime) FROM Record r")
    List<Record> list();

    @Query("SELECT r FROM Record r ORDER BY r.id DESC")
    Page<Record> findAll(Pageable of);
}