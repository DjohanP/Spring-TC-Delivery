package com.pbkk.finalproject.tcdelivery.model;

import java.util.Date;

public class ReturnUser {
	private Integer user_id;
	private String name;
	private String username;
	private Integer role;
	private Integer status;
	private String email;
	private String phone_number;
	private Date created_at;
	
	public ReturnUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReturnUser(Integer user_id, String name, String username, Integer role, Integer status, String email,
			String phone_number, Date created_at) {
		//super();
		this.user_id = user_id;
		this.name = name;
		this.username = username;
		this.role = role;
		this.status = status;
		this.email = email;
		this.phone_number = phone_number;
		this.created_at = created_at;
	}
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		if(this.role == 1) {
			return "Customer";
		}
		else if(this.role == 2) {
			return "Restaurant";
		}
		else if(this.role == 3) {
			return "Admin";
		}
		else if(this.role == 4) {
			return "Driver";
		}
		return null;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
