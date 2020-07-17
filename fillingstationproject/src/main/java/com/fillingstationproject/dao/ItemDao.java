package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemDao extends JpaRepository<Item,Integer> {

    @Query(value = "SELECT new Item  (i.id,i.name,i.quantity,i.rop,i.roq) FROM Item i")
    List<Item> list();

    @Query("SELECT i FROM Item i ORDER BY i.id DESC")
    Page<Item> findAll(Pageable of);

    @Query("SELECT i FROM Item i WHERE i.code= :code")
    Item findByCode(@Param("code") String code);

    @Query(value = "SELECT (p.itemId) FROM Porderitem p WHERE p.porderId.id = :porderId")
    List<Item> listbyporder(@Param("porderId") Integer porderId);

    @Query(value = "SELECT max(i.code)+1 FROM Item i")
    String lastItemno();

//    @Query(value="SELECT (co.itemId) FROM corderitem co WHERE co.corderId in (SELECT c.orderno FROM corder c WHERE c.corderId.id=:corderid)")
//    List<Item> listbycorder(@Param("corderId") Integer corderId );

    @Query("SELECT new Item(i.id,i.name,i.quantity,i.rop,i.roq)FROM Item i WHERE i.itemtypeId in (SELECT su.itemtypeId FROM Supply su WHERE su.supplierId.id =:supplierid)")
    List<Item> listbysupplier(@Param("supplierid") Integer supplierid);

    //SELECT * FROM orderandsales.item where id in ( select item_id from orderandsales.corderitem where corder_id = 7 );
//    @Query("SELECT new Item( i.id,i.name,i.quantity,i.rop,i.roq,i.currentsalesprice) FROM Item i WHERE i.id in ( SELECT co.itemId.id FROM Corderitem co WHERE co.corderId.id =:corderId)")
//    List<Item> listByCorder(@Param("corderId") Integer corderId);

    @Query(value = "SELECT (c.itemId) FROM Corderitem c WHERE c.corderId.id = :corderId")
    List<Item> listbycorder(@Param("corderId") Integer corderId);

    @Query("Select new Item (i.id,i.name,i.quantity,i.rop,i.roq) from Item i where i.subcategoryId.categoryId.id = :categoryId")
    List<Item> listbycategory(@Param("categoryId") Integer categoryId);


}
