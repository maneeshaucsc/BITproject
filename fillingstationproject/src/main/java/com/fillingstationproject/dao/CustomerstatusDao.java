package com.fillingstationproject.dao;

import com.fillingstationproject.entity.Customerstatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerstatusDao extends JpaRepository<Customerstatus,Integer> {

    @Query(value="SELECT new Customerstatus (cs.id,cs.status) FROM Customerstatus cs")
    List<Customerstatus> list();

    @Query("SELECT cs FROM Customerstatus cs WHERE cs.status= :status")
    Customerstatus findByStatus(@Param("status")String status);

}
