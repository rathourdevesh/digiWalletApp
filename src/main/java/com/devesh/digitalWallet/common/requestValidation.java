package com.devesh.digitalWallet.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.devesh.digitalWallet.dao.usersModel;
import com.devesh.digitalWallet.service.usersDataService;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class requestValidation {
	
	@Autowired
	private usersDataService userService;
	
	public boolean validateCreateUserRequest(usersModel users) {
//    	Query query = new Query(where("userName").is(users.getUserName());
//    	MongoTemplate mongo = new MongoTemplate();
//    	List<usersModel> data = mongo.find(query, usersModel.class);
		if((users.getUserName().isBlank()) || (users.getPassword().isEmpty()) ) {
			return false;
		}
		if (userService.validateIfUserExists(users)){
			return false;
		}
		return true;
	}

}
