

package com.fillingstationproject.dao;


        import com.fillingstationproject.entity.Userstatus;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;

        import java.util.List;


public interface UserstatusDao extends JpaRepository<Userstatus, Integer>
{

    @Query(value="SELECT new Userstatus(u.id,u.name) FROM Userstatus u")
      List<Userstatus> list();


}