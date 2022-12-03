package com.devesh.digitalWallet;


import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.devesh.digitalWallet.utils.CreateAcctNum;

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

	@Autowired
	private CreateAcctNum createAcctNum;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Content-type", "aplication/json");
    }

    @RequestMapping(value = "/register", method=RequestMethod.POST)
	public ResponseEntity<Object> registerUser(@RequestBody usersModel newUser) {
    	log.info("Create user request for ".concat(newUser.getUserName()));
    	
//    	Validating UserId.
    	boolean val = userService.validateIfUserExists(newUser);
 
    	log.info("Request Validated " + val);
    	if(val) {
    		return responseObject.generateResponse(HttpStatus.CONFLICT, false, "Error Creating User!!");
    	}
    	else {
    		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    		String acctNum = createAcctNum.generateAcctNum(newUser.getAccountType());
    		newUser.setAccountNumber(acctNum);
    		newUser.setBalance(0);
        	userService.createUser(newUser);
        	HashMap<String, Object> meta = new HashMap<String, Object>();
        	meta.put("Account Number", acctNum);
    		return responseObject.generateResponse(
    				HttpStatus.CREATED, true,
    				String.format("User %s created", newUser.getUserName()),
    				meta);
    	}
	}

    @RequestMapping(value = "/authenticate", method=RequestMethod.POST)
	public ResponseEntity<Object> authenticateUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userName = authentication.getName();
    	usersModel user = userService.getUserByName(userName);
    	String jwt = jwtTokenUtil.generateToken(user);
    	HashMap<String, Object> meta = new HashMap<String, Object>();
    	meta.put("jwt", jwt);
    	return responseObject.generateResponse(
    			HttpStatus.OK, true,
    			"User authenticated",
    			meta);
    }

    @RequestMapping(value = "/user", method=RequestMethod.GET)
	public ResponseEntity<Object> loginUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String user = authentication.getName();
    	log.info("current auth user "+ user);
    	usersModel data = userService.getUserByName(user);
    	return responseObject.generateResponse(
    			HttpStatus.OK, true,
    			"User Found!!",
    			data.getUserDetails());
    }
    
}



