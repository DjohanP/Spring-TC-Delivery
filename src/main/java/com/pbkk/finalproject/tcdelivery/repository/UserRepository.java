package com.pbkk.finalproject.tcdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbkk.finalproject.tcdelivery.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.id = ?1")
	User findByIduser(Integer id);
	
	@Query("select u from User u where u.userName = :userName")
	User findByUsername(@Param("userName") String userName);
	
	@Query("select u from User u where u.email = :email")
	User findByEmail(@Param("email") String email);
	
	@Query("select u from User u where u.email = :email and u.password=:password")
	User findByEmailandPassword(@Param("email") String email,
								@Param("password") String password);
	
}
