package com.pbkk.finalproject.tcdelivery.restapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbkk.finalproject.tcdelivery.model.Testuser;
import com.pbkk.finalproject.tcdelivery.repository.TestuserRepository;

@RestController
@RequestMapping("/testuser")
public class TestuserController {
	
	@Autowired
	TestuserRepository testuserRepository;
	
	@ResponseBody
	@RequestMapping("")
	public List<Testuser> getAllUsers() {
		return testuserRepository.findAll();
	}
	
}
