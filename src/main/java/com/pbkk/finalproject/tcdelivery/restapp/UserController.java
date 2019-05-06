package com.pbkk.finalproject.tcdelivery.restapp;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pbkk.finalproject.tcdelivery.aop.AdminTokenRequired;
import com.pbkk.finalproject.tcdelivery.aop.UserTokenRequired;
import com.pbkk.finalproject.tcdelivery.dao.TokenDAO;
import com.pbkk.finalproject.tcdelivery.dao.UserDAO;
import com.pbkk.finalproject.tcdelivery.model.User;
import com.pbkk.finalproject.tcdelivery.service.SecurityService;
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
	
	public  Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public void validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        if(matcher.find()==false)
        {
        	throw new IllegalArgumentException("Email doesn't match");
        }
	}
	
	public void checkAvailable(String username,String email)
	{
		if(userDAO.checkUsername(username)==false)
		{
			throw new IllegalArgumentException("Username has been used");
		}
		if(userDAO.checkEmail(email)==false)
		{
			throw new IllegalArgumentException("Email has been used");
		}
	}
	
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping("")
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "/register/customer", method = RequestMethod.POST)
	public Map<String, Object> registerCustomer(
				@RequestParam(value = "username") String username,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "email") String email,
				@RequestParam(value = "password") String password
			) 
	{
		checkAvailable(username,email);
		validate(email);
		
		User usr=new User();
		usr.setName(name);
		usr.setUserName(username);
		usr.setPassword(password);
		usr.setEmail(email);
		usr.setStatus(1);
		usr.setRole(1);
		usr.setCreatedAt(new Date());
		userDAO.save(usr);
		return Util.getSuccessResult();
	}
	
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping(value = "/register/restaurant", method = RequestMethod.POST)
	public Map<String, Object> registerRestaurant(
				@RequestParam(value = "username") String username,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "email") String email,
				@RequestParam(value = "password") String password
			) 
	{
		checkAvailable(username,email);
		validate(email);
		
		User usr=new User();
		usr.setName(name);
		usr.setUserName(username);
		usr.setPassword(password);
		usr.setEmail(email);
		usr.setStatus(1);
		usr.setRole(2);
		usr.setCreatedAt(new Date());
		userDAO.save(usr);
		return Util.getSuccessResult();
	}
	
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping(value = "/register/admin", method = RequestMethod.POST)
	public Map<String, Object> registerAdmin(
				@RequestParam(value = "username") String username,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "email") String email,
				@RequestParam(value = "password") String password
			) 
	{
		checkAvailable(username,email);
		validate(email);
		
		User usr=new User();
		usr.setName(name);
		usr.setUserName(username);
		usr.setPassword(password);
		usr.setEmail(email);
		usr.setStatus(1);
		usr.setRole(3);
		usr.setCreatedAt(new Date());
		userDAO.save(usr);
		return Util.getSuccessResult();
	}
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(
				@RequestParam(value = "email") String email,
				@RequestParam(value = "password") String password
			) 
	{
		User user=userDAO.login(email, password);
		String subject = user.getId()+"="+user.getRole();
		String token = securityService.createToken(subject, (60 * 1000 * 60),user.getId()); // 60 minutes expiry time
		return Util.getSuccessResult(token);
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Map<String, Object> logout(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password
		) 
	{
		ServletRequestAttributes reqAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = reqAttributes.getRequest();
		
		// checks for token in request header
		String tokenInHeader = request.getHeader("token");
		
		if(StringUtils.isEmpty(tokenInHeader)){
			throw new IllegalArgumentException("Empty token");
		}
		
		//System.out.println(tokenInHeader);
		tokenDAO.delete(tokenInHeader);
		return Util.getSuccessResult();
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		User get=userDAO.findById(id);
		return get;
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/customer/")
	public List<User> getCustomer() {
		List<User> get=userDAO.findCustomer(null);
		return get;
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/customer/{id}")
	public List<User> getCustomerById(@PathVariable("id") String idS) {
		Integer id=null;
		if(!idS.equals(""))
		{
			id=Integer.valueOf(idS);
		}
		List<User> get=userDAO.findCustomer(id);
		return get;
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/restaurant/")
	public List<User> getRestaurant() {
		List<User> get=userDAO.findRestaurant(null);
		return get;
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping("/restaurant/{id}")
	public List<User> getRestaurantById(@PathVariable("id") String idS) {
		Integer id=null;
		if(!idS.equals(""))
		{
			id=Integer.valueOf(idS);
		}
		List<User> get=userDAO.findRestaurant(id);
		return get;
	}

}
