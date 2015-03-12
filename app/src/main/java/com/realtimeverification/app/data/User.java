package com.realtimeverification.app.data;

/**
 * Created by vaal on 3/10/2015.
 */
public class User {

	private String fullName;
	private String email;
	private String contactNo;
	private String active;

	public User(String fullName, String email, String contactNo, String active){
		setFullName(fullName);
		setEmail(email);
		setContactNo(contactNo);
		setActive(active);
	}

	private void setFullName(String fullName){
		this.fullName = fullName;
	}

	private void setEmail(String email){
		this.email = email;
	}

	private void setContactNo(String contactNo){
		this.contactNo= contactNo;
	}

	private void setActive(String active){
		this.active = active;
	}
}
