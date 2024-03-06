package com.telspiel.saswat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	public static <T> String convertObjectToJsonString(T inputObject) {
		String jsonString = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(inputObject);
		} catch (Exception e) {
			logger.info(" JsonUtils: convertObjectToJsonString {}",e);
		}
		return jsonString;
	}

}
