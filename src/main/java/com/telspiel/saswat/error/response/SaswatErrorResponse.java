package com.telspiel.saswat.error.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.telspiel.saswat.utils.SaswatStatus;

public class SaswatErrorResponse {
	
	private static final Logger logger = LoggerFactory.getLogger(SaswatErrorResponse.class);

	public static SaswatStatus returnBadRequest() {
		SaswatStatus response = new SaswatStatus();
		response.setStatus("FAILURE");
		response.setCode("-6");
		response.setDesc("Saswat DTO should not be null");
		return response;
	}
	
	public static SaswatStatus returnInternalServerErrorResponse(Exception e) {
		SaswatStatus response = new SaswatStatus();
		response.setStatus("FAILURE");
		response.setCode("Internal Server Error Response.!!!");
		response.setDesc(e.getMessage());
		return response;
	}
	
	public static SaswatStatus returnDefault() {
		String msg = "API is not Available for this Name.!!!";
		SaswatStatus response = new SaswatStatus();
		response.setStatus("FAILURE");
		response.setDesc(msg);
		response.setCode("Didn't hit API");
		return response;
	}

}
