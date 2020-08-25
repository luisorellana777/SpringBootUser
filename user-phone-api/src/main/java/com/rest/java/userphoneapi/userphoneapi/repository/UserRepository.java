package com.rest.java.userphoneapi.userphoneapi.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.java.userphoneapi.userphoneapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Modifying
	@Transactional
	@Query(value = "select * from User u where u.email = :email and u.password = :password", nativeQuery = true)
	Collection<User> login(@Param("email") String email, @Param("password") String password);
	
	@Modifying
	@Transactional
	@Query(value = "select * from User u where u.email = :email", nativeQuery = true)
	Collection<User> getUserByEmail(@Param("email") String email);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update User u set u.isActive = :isActive where u.email = :email")
	void updateIsActiveByEmail(@Param("email") String email, @Param("isActive") Boolean isActive);
}
