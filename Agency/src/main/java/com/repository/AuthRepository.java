package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Authority;

public interface AuthRepository extends JpaRepository<Authority, Long>{
	Authority findByName(String name);
}
