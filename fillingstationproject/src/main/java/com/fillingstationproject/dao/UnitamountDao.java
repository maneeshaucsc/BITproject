package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Unitamount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitamountDao extends JpaRepository<Unitamount,Integer> {

    @Query(value="SELECT new Unitamount (ua.id,ua.amount) FROM Unitamount ua")
    List<Unitamount> list();

   /* @Query(value="SELECT new Unitamount (ua.id,ua.amount) FROM Unitamount ua where ua.subcategoryId.id=:subcategoryId")
    List<Unitamount> listByScategory(@Param("subcategoryId") Integer subcategoryId);*/

//    @Query(value="SELECT new Unitamount(ua.id,ua.amount) FROM Unitamount ua where ua in(SELECT s.unitamountId from Subcategoryunitamount s where s.subcategoryId.id=:subcategoryId) ")
//    List<Unitamount> listbyscategory(@Param("subcategoryId")Integer subcategoryId);

    @Query("SELECT ua FROM Unitamount ua ORDER BY ua.id DESC")
    Page<Unitamount> findAll(Pageable of);

}
