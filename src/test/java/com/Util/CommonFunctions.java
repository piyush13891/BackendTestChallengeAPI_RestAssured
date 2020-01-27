package com.Util;

import org.apache.commons.validator.routines.EmailValidator;

public class CommonFunctions {
	
	/**
	 * To validate email address format 
	 * 
	 * @param email address
	 * @return validation status
	 */
	public boolean validateEmailApache(String email){
		email = email.trim();
		EmailValidator eValidator = EmailValidator.getInstance();
		if(eValidator.isValid(email)){
			return true;
		}else{
			return false;
		}
	}	
}
