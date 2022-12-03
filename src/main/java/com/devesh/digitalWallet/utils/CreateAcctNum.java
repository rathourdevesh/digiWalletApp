package com.devesh.digitalWallet.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CreateAcctNum {
	
	private String bankInitials = "DW";

	public String generateAcctNum(String AcctType) {
		String curentTimestamp = Long.toString(System.currentTimeMillis());
		String currYear = new SimpleDateFormat("yyyy").format(new Date());
		String acctNum = bankInitials + currYear + AcctType + 
				curentTimestamp.substring(curentTimestamp.length() - 6);
		return acctNum;	
	}

}
