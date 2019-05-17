package com.pbkk.finalproject.tcdelivery.restapp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbkk.finalproject.tcdelivery.aop.AdminTokenRequired;
import com.pbkk.finalproject.tcdelivery.aop.UserTokenRequired;
import com.pbkk.finalproject.tcdelivery.dao.TokenDAO;
import com.pbkk.finalproject.tcdelivery.dao.UserDAO;
import com.pbkk.finalproject.tcdelivery.model.ReturnUser;
import com.pbkk.finalproject.tcdelivery.model.User;
import com.pbkk.finalproject.tcdelivery.service.SecurityService;
import com.pbkk.finalproject.tcdelivery.service.UserService;
import com.pbkk.finalproject.tcdelivery.util.Util;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	TokenDAO tokenDAO;
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	UserService userService;
		
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping("")
	public List<ReturnUser> getAllUsers() {
		List<User> get = userDAO.findAll();
		
		List<ReturnUser> returnUser = new ArrayList<ReturnUser>();
		for(User usr : get){
			returnUser.add(new ReturnUser(usr.getId(), usr.getName(), usr.getUserName(), usr.getRole(), usr.getStatus(), usr.getEmail(), usr.getPhoneNumber(), usr.getCreatedAt()));
		}
		
		return returnUser;
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/{id}")
	public ReturnUser getUser(@PathVariable("id") Integer id) {
		User usr = userDAO.findById(id);
		
		if(usr != null) {
			ReturnUser returnUser = new ReturnUser(usr.getId(), usr.getName(), usr.getUserName(), usr.getRole(), usr.getStatus(), usr.getEmail(), usr.getPhoneNumber(), usr.getCreatedAt());
			return returnUser;
		}
		else {
			ReturnUser returnUser = null;
			return returnUser;
		}
	}
	
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteUser(
			@PathVariable("id") Integer userid) {
		
		//Find user id
		User user=userDAO.findById(userid);
		if(user==null)
		{
			return Util.getErrorResult("Username not Found");
		}
				
		Map<String, Object> map = new LinkedHashMap<>();   
		userDAO.deleteUser(userid);    
	    map.put("result", "deleted");
	    return map;
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping(value = "/username", method = RequestMethod.POST)
	public Map<String, Object> checkUsername(
				@RequestParam(value = "username") String username
			) 
	{
		if(userDAO.checkUsername(username)==false)
		{
			return Util.getErrorResult("Username already used");
		}
		return Util.getSuccessResult("Username available");
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public Map<String, Object> checkEmail(
				@RequestParam(value = "email") String email
			) 
	{
		if(userDAO.checkEmail(email)==false)
		{
			return Util.getErrorResult("Email already used");
		}
		return Util.getSuccessResult("Email available");
	}
}
