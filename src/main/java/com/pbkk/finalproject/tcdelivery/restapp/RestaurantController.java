package com.pbkk.finalproject.tcdelivery.restapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

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
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ReturnUser> registerRestaurant(
				@RequestParam(value = "username") String username,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "email") String email,
				@RequestParam(value = "password") String password,
				@RequestParam(value = "phone_number") String phoneNumber
			) 
	{
		userService.checkAvailable(username,email);
		userService.validate(email);
		
		User usr=new User();
		usr.setName(name);
		usr.setUserName(username);
		usr.setPassword(password);
		usr.setEmail(email);
		usr.setPhoneNumber(phoneNumber);
		usr.setStatus(1);
		usr.setRole(2);
		usr.setCreatedAt(new Date());
		userDAO.save(usr);
		
		return new ResponseEntity<ReturnUser>(new ReturnUser(usr.getId(), usr.getName(), usr.getUserName(), usr.getRole(), usr.getStatus(), usr.getEmail(), usr.getPhoneNumber(), usr.getCreatedAt()), HttpStatus.CREATED);
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/")
	public List<ReturnUser> getRestaurant() {
		List<User> get=userDAO.findRestaurant(null);

		List<ReturnUser> returnUser = new ArrayList<ReturnUser>();
		for(User usr : get){
			returnUser.add(new ReturnUser(usr.getId(), usr.getName(), usr.getUserName(), usr.getRole(), usr.getStatus(), usr.getEmail(), usr.getPhoneNumber(), usr.getCreatedAt()));
		}
		
		return returnUser;
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/{id}")
	public List<ReturnUser> getRestaurantById(@PathVariable("id") String idS) {
		Integer id=null;
		if(!idS.equals(""))
		{
			id=Integer.valueOf(idS);
		}
		List<User> get=userDAO.findRestaurant(id);

		List<ReturnUser> returnUser = new ArrayList<ReturnUser>();
		for(User usr : get){
			returnUser.add(new ReturnUser(usr.getId(), usr.getName(), usr.getUserName(), usr.getRole(), usr.getStatus(), usr.getEmail(), usr.getPhoneNumber(), usr.getCreatedAt()));
		}
		
		return returnUser;
	}
}
