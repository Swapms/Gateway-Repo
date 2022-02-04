package com.vwits.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vwits.gateway.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{

	@Query("select a from User a where email = ?1")
	User findByEmail(String emailId);
}
