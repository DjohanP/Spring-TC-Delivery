package com.pbkk.finalproject.tcdelivery.restapp;

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
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		User get=userDAO.findById(id);
		return get;
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
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public Map<String, Object> changePassword(
				@RequestParam(value = "oldpassword") String oldPassword,
				@RequestParam(value = "newpassword") String newPassword,
				@RequestParam(value = "confirmpassword") String confirmPassword
			) 
	{
		String userIdString=securityService.getUserId();
		Integer userId=Integer.valueOf(userIdString);
		
		User user=userDAO.login(userId, oldPassword);
		if(user==null)
		{
			return Util.getErrorResult("Username or Password is Not Match");
		}
		if(oldPassword.equals("")||newPassword.equals("")||confirmPassword.equals(""))
		{
			return Util.getErrorResult("All fields must be filled in");
		}
		if(!newPassword.equals(confirmPassword))
		{
			return Util.getErrorResult("New password not equal with confirm password");
		}
		
		userDAO.updatePasswordUser(userId,newPassword);
		
		
		return Util.getSuccessResult("Successfully updated user password");
	}
	
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping(value = "/password/{id}", method = RequestMethod.PUT)
	public Map<String, Object> changePasswordAdmin(
				@PathVariable("id") Integer id,
				@RequestParam(value = "newpassword") String newPassword
			) 
	{
		User user=userDAO.findById(id);//find by id
		if(user==null)
		{
			return Util.getErrorResult("Username not Found");
		}
		
		userDAO.updatePasswordUser(id,newPassword);
		
		return Util.getSuccessResult("Successfully updated user password");
		
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
			return Util.getErrorResult("Username available");
		}
		return Util.getSuccessResult("Username not available");
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
			return Util.getErrorResult("Username available");
		}
		return Util.getSuccessResult("Username not available");
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping(value = "/biodata", method = RequestMethod.PUT)
	public Map<String, Object> changeBiodata(
				@RequestParam(value = "username") String userName,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "email") String email,
				@RequestParam(value = "phone_number") String phoneNumber
			) 
	{
		String userIdString=securityService.getUserId();
		Integer userId=Integer.valueOf(userIdString);
		
		userDAO.updateUser(userId, name, userName, email, phoneNumber);
		
		return Util.getSuccessResult("Biodata was updated");
	}
	
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping(value = "/biodata/{id}", method = RequestMethod.PUT)
	public Map<String, Object> changeBiodataAdmin(
				@PathVariable("id") Integer id,
				@RequestParam(value = "username") String userName,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "email") String email,
				@RequestParam(value = "phone_number") String phoneNumber
			)
	{
		//Find user id
		User user=userDAO.findById(id);
		if(user==null)
		{
			return Util.getErrorResult("Username not Found");
		}
		
		userDAO.updateUser(id, name, userName, email, phoneNumber);
		
		return Util.getSuccessResult("Biodata was updated");
	}
}
