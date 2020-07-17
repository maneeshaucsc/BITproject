package com.fillingstationproject.dao;


import com.fillingstationproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface RoleDao extends JpaRepository<Role, Integer>
{

    @Query(value="SELECT new Role(r.id,r.name) FROM Role r")
    List<Role> list();

}