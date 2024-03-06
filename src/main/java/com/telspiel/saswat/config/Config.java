package com.telspiel.saswat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.telspiel.saswat.serviceImpl.BulkSmsServiceImpl;
import com.telspiel.saswat.serviceImpl.SaswatServiceImpl;
import com.telspiel.saswat.serviceImpl.SingleSmsServiceImpl;

public class Config {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public SaswatServiceImpl saswatServiceImpl() {
		return new SaswatServiceImpl();
	}

	@Bean
	public SingleSmsServiceImpl singleSmsServiceImpl() {
		return new SingleSmsServiceImpl();
	}

	@Bean
	public BulkSmsServiceImpl bulkSmsServiceImpl() {
		return new BulkSmsServiceImpl();
	}

}
