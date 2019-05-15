package com.pbkk.finalproject.tcdelivery.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pbkk.finalproject.tcdelivery.aop.UserTokenRequired;
import com.pbkk.finalproject.tcdelivery.dao.TokenDAO;
import com.pbkk.finalproject.tcdelivery.model.Token;
import com.pbkk.finalproject.tcdelivery.service.SecurityService;

@Aspect
@Component
public class UserTokenRequiredAspect {
	@Autowired
	TokenDAO tokenDAO;
	
	@Autowired
	SecurityService securityService;
	
	@Before("@annotation(userTokenRequired)")
	public void tokenRequiredWithAnnotation(UserTokenRequired userTokenRequired) throws Throwable{
		
		ServletRequestAttributes reqAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = reqAttributes.getRequest();
		
		// checks for token in request header
		String tokenInHeader = request.getHeader("token");
		Token tokenDB=tokenDAO.getByStringToken(tokenInHeader);
		
		if(StringUtils.isEmpty(tokenInHeader)){
			throw new IllegalArgumentException("Empty token");
		}
		if(tokenDB==null)
		{
			throw new IllegalArgumentException("User token is not authorized");
		}
		
		
		DecodedJWT verifyToken=securityService.verifyToken(tokenInHeader);
		
		if(verifyToken == null){
			throw new IllegalArgumentException("Token Error : Claim is null");
		}
		
		/*Map<String, Claim> claims = verifyToken.getClaims();    //Key is the Claim name
		Claim claim = claims.get("username");
		Claim claimT=verifyToken.getClaim("username");*/
		
		/*Claim role=verifyToken.getClaim("role");
		String roleUser=role.asString();*/
		
		if(verifyToken.getClaim("username") == null){
			throw new IllegalArgumentException("User token is not authorized");
		}		
	}
}
