package com.devesh.digitalWallet.dao;

import java.time.LocalDateTime;
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
	private LocalDateTime creationDate = java.time.LocalDateTime.now();
	private long phoneNumber;
	private String uniqueId;
	private String accountType;
	private String accountNumber;
	private String userType;
	private String password;
	private HashMap<String, String> address;
	private long balance;

	public usersModel(String userId, String userName, String name, LocalDateTime creationDate, long phoneNumber,
			String uniqueId, String accountType, String accountNumber, String userType, String password,
			HashMap<String, String> address, long balance) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.creationDate = creationDate;
		this.phoneNumber = phoneNumber;
		this.uniqueId = uniqueId;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.userType = userType;
		this.password = password;
		this.address = address;
		this.balance = balance;
	}

	public HashMap<String, Object> getUserDetails() {
    	HashMap<String, Object> userMap = new HashMap<String, Object>();
    	userMap.put("userName", this.userName);
    	userMap.put("name", this.name);
    	userMap.put("phoneNumber", this.phoneNumber);
    	userMap.put("uniqueId", this.uniqueId);
    	userMap.put("accountType", this.accountType);
    	userMap.put("accountNumber", this.accountNumber);
    	userMap.put("balance", this.balance);
    	return userMap;
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
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
}
