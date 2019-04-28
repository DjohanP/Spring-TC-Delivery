package com.pbkk.finalproject.tcdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbkk.finalproject.tcdelivery.model.Testuser;
import org.springframework.stereotype.Repository;

@Repository
public interface TestuserRepository extends JpaRepository<Testuser, Integer>{

}
