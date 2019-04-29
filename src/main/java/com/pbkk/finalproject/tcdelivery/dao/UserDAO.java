package com.pbkk.finalproject.tcdelivery.dao;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkk.finalproject.tcdelivery.model.User;
import com.pbkk.finalproject.tcdelivery.repository.UserRepository;

@Service
public class UserDAO {
	
	@Autowired
	UserRepository userRepository;
	
	public User save(User usr) {
		return userRepository.save(usr);
	}
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	public User findBooksById(Integer id) 
	{
        return userRepository.findByIduser(id);
    }
	
	public User login(String email,String password)
	{
		User user=userRepository.findByEmailandPassword(email, DigestUtils.sha256Hex(password));
		if(user==null)
		{
			throw new IllegalArgumentException("Username or Password is Not Match");
		}
		return user;
	}
	
	public boolean checkUsername(String userName)
	{
		User checkUser=userRepository.findByUsername(userName);
		if(checkUser==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkEmail(String email)
	{
		User checkUser=userRepository.findByEmail(email);
		if(checkUser==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
