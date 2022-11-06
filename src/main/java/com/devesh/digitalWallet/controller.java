package com.devesh.digitalWallet;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devesh.digitalWallet.common.responseObject;
import com.devesh.digitalWallet.dao.usersModel;
import com.devesh.digitalWallet.service.usersDataService;
import com.devesh.digitalWallet.utils.jwtUtil;

import ch.qos.logback.classic.Logger;


@RestController
public class controller {
	
	Logger log = (Logger) LoggerFactory.getLogger(controller.class);

	@Autowired
	private usersDataService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private jwtUtil jwtTokenUtil;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Content-type", "aplication/json");
    }

    @RequestMapping(value = "/register", method=RequestMethod.POST)
	public responseObject registerUser(@RequestBody usersModel newUser) {
    	log.info("Create user for ".concat(newUser.getUserName()));
    	
//    	Validating UserId.
    	boolean val = userService.validateIfUserExists(newUser);
 
    	log.info("Request Validated " + val);
    	if(val) {
    		return new responseObject(false,"Error Creating User!!");
    	}
    	else {
    		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        	userService.createUser(newUser);
    		return new responseObject(
    				true,
    				String.format("User %s created", newUser.getUserName())
    				);
    	}
	}
 
    @RequestMapping(value = "/authenticate", method=RequestMethod.POST)
	public responseObject authenticateUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userName = authentication.getName();
    	usersModel user = userService.getUserByName(userName);
    	String jwt = jwtTokenUtil.generateToken(user);
		return new responseObject(
				true,
				jwt
				);
    }
    
    @RequestMapping(value = "/login", method=RequestMethod.POST)
	public responseObject loginUser(@RequestBody usersModel user) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	System.out.println("current auth user "+ currentPrincipalName);
    	boolean val = userService.validateIfUserExists(user);
		return new responseObject(
				val,
				String.format("User logged in")
				);
    }
    
}



