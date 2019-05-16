package com.pbkk.finalproject.tcdelivery.restapp;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pbkk.finalproject.tcdelivery.aop.UserTokenRequired;
import com.pbkk.finalproject.tcdelivery.dao.TokenDAO;
import com.pbkk.finalproject.tcdelivery.dao.UserDAO;
import com.pbkk.finalproject.tcdelivery.model.User;
import com.pbkk.finalproject.tcdelivery.service.SecurityService;
import com.pbkk.finalproject.tcdelivery.service.UserService;
import com.pbkk.finalproject.tcdelivery.util.Util;

@RestController
public class TokenController {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	TokenDAO tokenDAO;
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public Map<String, Object> login(
				@RequestParam(value = "email") String email,
				@RequestParam(value = "password") String password
			) throws IOException 
	{
		User user=userDAO.login(email, password);
		String subject = user.getId()+"="+user.getRole();
		String token = securityService.createToken(subject,user.getRole(),user.getName(),user.getUserName(),user.getEmail(), user.getPhoneNumber(),user.getId(),(60 * 1000 * 60)); // 60 minutes expiry time
		return Util.getSuccessResult(token);
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping(value = "/revoke", method = RequestMethod.POST)
	public Map<String, Object> logout() 
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
	
}
