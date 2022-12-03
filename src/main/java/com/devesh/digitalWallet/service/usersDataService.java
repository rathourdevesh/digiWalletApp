package com.devesh.digitalWallet.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devesh.digitalWallet.dao.userRepository;
import com.devesh.digitalWallet.dao.usersModel;

@Service
public class usersDataService {
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private userRepository userRepository;


	public usersModel createUser(usersModel user) {
		
		return userRepository.save(user);
	}

	public usersModel getUserByName(String userName) {
		List<usersModel> user = userRepository.findbyUsername(userName);
		log.info("user count queried " + user.size());
		if(user.size() >= 1) {
			return user.get(0);
		}
		else {
			return null;
//			throw new UsernameNotFoundException(username);;
		}
	}

	public boolean validateIfUserExists(usersModel user) {
		if((user.getUserName().isBlank()) || (user.getPassword().isEmpty()) ) {
			return false;
		}
		usersModel data =  this.getUserByName(user.getUserName());
		log.info("Data retrived: " + data);
		if(data == null) {
			return false;
		}
		return true;
	}
}
