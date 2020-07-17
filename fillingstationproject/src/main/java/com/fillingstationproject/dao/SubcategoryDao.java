package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryDao extends JpaRepository<Subcategory,Integer> {

    @Query(value="SELECT new Subcategory (c.id,c.name) FROM Subcategory c where c.categoryId.id=:categoryId")
    List<Subcategory> listByCategory(@Param("categoryId") Integer categoryId);

    @Query(value="SELECT new Subcategory (s.id,s.name) FROM Subcategory s")
    List<Subcategory> list();

    @Query("SELECT s FROM Subcategory s ORDER BY s.id DESC")
    Page<Subcategory> findAll(Pageable of);

    @Query("SELECT s FROM Subcategory s WHERE s.name= :name")
    Subcategory findByName(@Param("name")String name);
}
