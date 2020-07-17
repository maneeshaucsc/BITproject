package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Tank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TankDao extends JpaRepository<Tank,Integer> {
    @Query(value="SELECT new Tank (t.id,t.code) FROM Tank t")
    List<Tank> list();

    @Query("SELECT t FROM Tank t ORDER BY t.id DESC")
    Page<Tank> findAll(Pageable of);

    @Query("SELECT t FROM Tank t WHERE t.code= :code")
    Tank findByCode(@Param("code")String code);
}
