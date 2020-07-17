package com.fillingstationproject.dao;


import com.fillingstationproject.entity.Category;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category,Integer> {

    @Query(value="SELECT new Category (c.id,c.name) FROM Category c")
    List<Category> list();

    @Query(value="SELECT new Category (c.id,c.name) FROM Category c where c.itemtypeId.id=:itemtypeId")
    List<Category> listByItype(@Param("itemtypeId") Integer itemtypeId);

    @Query("SELECT c FROM Category c ORDER BY c.id DESC")
    Page<Category> findAll(Pageable of);

    @Query("SELECT c FROM Category c WHERE c.name= :name")
    Category findByName(@Param("name")String name);
}
