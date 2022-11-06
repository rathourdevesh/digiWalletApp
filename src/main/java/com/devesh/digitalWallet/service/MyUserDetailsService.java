package com.devesh.digitalWallet.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devesh.digitalWallet.dao.usersModel;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private usersDataService usersDataService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		usersModel user = usersDataService.getUserByName(userName);
		System.out.println("user details service "+ user.getUserName()+" "+ user.getPassword());
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}
}
