package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandDao extends JpaRepository<Brand,Integer> {

    @Query(value="SELECT new Brand (b.id,b.name) FROM Brand b")
    List<Brand> list();

    @Query("SELECT b FROM Brand b ORDER BY b.id DESC")
    Page<Brand> findAll(Pageable of);

    @Query("SELECT b FROM Brand b WHERE b.name= :name")
    Brand findByName(@Param("name")String name);

//    @Query(value="SELECT new Brand (b.id,b.name) FROM Brand b where b.itemtypeId.id= :itemtypeId")
//    List<Brand> listByItype(@Param("itemtypeId") Integer itemtypeId);

}
