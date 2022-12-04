package com.devesh.digitalWallet.kafka;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaMsgProducer {

	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private KafkaTemplate<String, HashMap<String, Object>> kafkaTemplate;
	
    @Value(value = "${spring.kafka.user-topic}")
    private String userTopic;

	public void produceOrderData(HashMap<String, Object> userData) {

	    ListenableFuture<SendResult<String, HashMap<String, Object>>> future = 
	    		kafkaTemplate.send(userTopic, userData);
	    		
	    	    future.addCallback(new ListenableFutureCallback<SendResult<String, HashMap<String, Object>>>() {

	    	        @Override
	    	        public void onSuccess(SendResult<String, HashMap<String, Object>> result) {
	    	        	log.info("Sent message=[" + userData + 
	  	    	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	  	    	        }
	    	        @Override
	    	        public void onFailure(Throwable ex) {
	    	        	log.info("Unable to send message=[" 
	    	              + userData + "] due to : " + ex.getMessage());
	    	        }
	    	    });
	}
}
