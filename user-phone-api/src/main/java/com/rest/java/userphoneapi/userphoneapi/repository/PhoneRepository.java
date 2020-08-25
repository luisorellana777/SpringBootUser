package com.rest.java.userphoneapi.userphoneapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.java.userphoneapi.userphoneapi.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

	//@Modifying
	//@Transactional
	//@Query("delete from Course c where c.code = :code")
	//void deleteById(@Param("id") Integer id);
}
