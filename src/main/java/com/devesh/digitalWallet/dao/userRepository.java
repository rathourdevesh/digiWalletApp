package com.devesh.digitalWallet.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface userRepository extends MongoRepository<usersModel, String> {
	
	@Query("{userName : ?0}") 
	List<usersModel> findbyUsername(String userName);

}
