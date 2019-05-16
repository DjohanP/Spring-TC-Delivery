package com.pbkk.finalproject.tcdelivery.restapp;

import java.io.IOException;
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
import com.pbkk.finalproject.tcdelivery.dao.UserDAO;
import com.pbkk.finalproject.tcdelivery.model.User;
import com.pbkk.finalproject.tcdelivery.service.SecurityService;
import com.pbkk.finalproject.tcdelivery.util.Util;

@RestController
public class ProfileController {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	SecurityService securityService;
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public Map<String, Object> changePassword(
				@RequestParam(value = "oldpassword") String oldPassword,
				@RequestParam(value = "newpassword") String newPassword,
				@RequestParam(value = "confirmpassword") String confirmPassword
			) throws IOException 
	{
		Integer userId=securityService.getUserId();
		
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
	@RequestMapping(value = "/biodata", method = RequestMethod.PUT)
	public Map<String, Object> changeBiodata(
				@RequestParam(value = "username") String userName,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "email") String email,
				@RequestParam(value = "phone_number") String phoneNumber
			) throws IOException 
	{
		Integer userId=securityService.getUserId();
		
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
			return Util.getErrorResult("User id not found");
		}
		
		userDAO.updateUser(id, name, userName, email, phoneNumber);
		
		return Util.getSuccessResult("Biodata was updated");
	}
}
