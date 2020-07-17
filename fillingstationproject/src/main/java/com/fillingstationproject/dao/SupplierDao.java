package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierDao extends JpaRepository<Supplier,Integer> {
    @Query(value="SELECT new Supplier  (s.id,s.company) FROM Supplier s")
    List<Supplier> list();

    @Query("SELECT s FROM Supplier s ORDER BY s.id DESC")
    Page<Supplier> findAll(Pageable of);

   /* @Query(value="SELECT new Supplier (s.id,s.company) FROM Supplier s where s.brandId.id=:brandId")
    List<Supplier> listByBrand(@Param("brandId") Integer bradId);*/

   /*@Query(value="SELECT new Supplier (s.company) FROM Supplier s where s.id in (SELECT s.itemtypeId FROM Supply su " +
           "WHERE su.itemtypeId.id=: itemtypeID AND " +
           "(SELECT b.brandId FROM brand b WHERE b.brandId=:brandId))")
   List<Supplier>listByBrand(@Param("brandId") Integer brandId);*/

    @Query("SELECT s FROM Supplier s WHERE s.company= :company")
    Supplier findBySupname(@Param("company")String company);

    @Query("SELECT s FROM Supplier s WHERE s.contactno= :contactno")
    Supplier findByConnumber(@Param("contactno")Integer contactno);

    @Query(value = "SELECT concat('R',lpad(substring(max(s.regno),2,5)+1,5,'0'))FROM Supplier s", nativeQuery=true)
    String lastRegno();
}
