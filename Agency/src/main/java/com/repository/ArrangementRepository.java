package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Arrangement;

public interface ArrangementRepository extends JpaRepository<Arrangement,Long>{
	@Query("SELECT a FROM Arrangement a WHERE a.user.username LIKE %:username%")
	List<Arrangement> findByUser(@Param("username")String username);
}
