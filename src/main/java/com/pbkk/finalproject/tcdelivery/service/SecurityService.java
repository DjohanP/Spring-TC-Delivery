package com.pbkk.finalproject.tcdelivery.service;

public interface SecurityService {
	String createToken(String subject,int roleUser,String name,String username, String email,Integer userId,long ttlMillis);
	String getSubject(String token);
	String getUserId();
	String getRole();
}
