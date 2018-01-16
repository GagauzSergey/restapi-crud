package com.restapi.crud.dao;

import com.restapi.crud.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TesterDao extends JpaRepository <Tester, Long> {

    @Query("SELECT u FROM Tester u where u.lastname = :lastname")
    Tester findTesterByLastName(@Param("lastname") String lastname);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Tester u WHERE u.lastname = :lastname")
    boolean existsTesterByLastName(@Param("lastname") String lastname);

}
