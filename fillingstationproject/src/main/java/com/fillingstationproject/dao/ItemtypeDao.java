package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Itemtype;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemtypeDao extends JpaRepository<Itemtype,Integer> {

    @Query(value="SELECT new Itemtype (it.id,it.type,it.brandId) FROM Itemtype it")
    List<Itemtype> list();

    @Query("SELECT it FROM Itemtype it ORDER BY it.id DESC")
    Page<Itemtype> findAll(Pageable of);

    @Query("SELECT it FROM Itemtype it WHERE it.type= :type")
    Itemtype findByType(@Param("type")String type);

    @Query("SELECT new Itemtype(it.id,it.type,it.brandId)FROM Itemtype it WHERE it in" +
            "(SELECT su.itemtypeId FROM Supply su WHERE su.supplierId.id =:supplierid)")
    List<Itemtype> listbysupplier(@Param("supplierid")Integer supplierid);

//    @Query(value="SELECT DISTINCT(p.itemtypeId) FROM Porderitem p WHERE p.porderId.id = :porderId")
//  List<Itemtype> listbyporder(@Param("porderId") Integer porderId );

//    @Query(value="SELECT new Itemtype (it.id,it.type) FROM Itemtype it where it in (select poi.porderId from Porderitem poi where poi.categoryId.id=:categoryId)")
//    List<Itemtype> listByCategory(@Param("categoryId") Integer categoryId);
//    @Query("SELECT new Itemtype(it.id,it.type,it.brandId)FROM Itemtype it WHERE it in" +
//            "(SELECT coi.itemtypeId FROM Corderitemtype coi WHERE coi.customerId.id =:customerid)")
//    List<Itemtype> listbycustomer(@Param("customerid")Integer customerid);
}
