package com.devesh.digitalWallet.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class responseObject {

	public static ResponseEntity<Object> generateResponse(HttpStatus status,boolean success, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        	map.put("success", success);
            map.put("message", message);

            return new ResponseEntity<Object>(map,status);
    }
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status,boolean success, String message, HashMap<String, Object> meta) {
        Map<String, Object> map = new HashMap<String, Object>();
        	map.put("success", success);
            map.put("message", message);
            map.put("response", meta);

            return new ResponseEntity<Object>(map,status);
    }
}
