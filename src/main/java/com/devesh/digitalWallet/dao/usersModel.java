package com.devesh.digitalWallet.dao;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.Id;


public class usersModel {
//	{
//	    "userName": "testuser001",
//	    "password": "pass",
//	    "name": "test user",
//	    "address": {
//	        "addr1": "room 01,flat 01",
//	        "City": "Mumbai"
//	    },
//	    "phoneNo": 12345,
//	    "uniqueId": "xxxxxx001",
//	    "accountType": "SB",
//	    "userType": "customer"
//	}

	@Id
	private  String userId;
	private String userName;
	private String name;
	private Date creationDate = new Date();
	private long phoneNumber;
	private String uniqueId;
	private String accountType;
	private String userType;
	private String password;
	private HashMap<String, String> address;

	public usersModel(String userId, String userName, String name, Date creationDate, long phoneNumber, String uniqueId,
			String accountType, String userType, String password, HashMap<String, String> address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.creationDate = creationDate;
		this.phoneNumber = phoneNumber;
		this.uniqueId = uniqueId;
		this.accountType = accountType;
		this.userType = userType;
		this.password = password;
		this.address = address;
	}

	public HashMap<String, String> getAddress() {
		return address;
	}
	public void setAddress(HashMap<String, String> address) {
		this.address = address;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
