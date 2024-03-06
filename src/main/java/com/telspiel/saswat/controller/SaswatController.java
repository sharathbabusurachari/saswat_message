package com.telspiel.saswat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telspiel.saswat.error.response.SaswatErrorResponse;
import com.telspiel.saswat.service.SaswatOtpService;
import com.telspiel.saswat.service.SaswatService;
import com.telspiel.saswat.utils.JsonUtils;
import com.telspiel.saswat.utils.SaswatDto;
import com.telspiel.saswat.utils.SaswatStatus;

@RestController
@RequestMapping("/saswat")
public class SaswatController {

	private static final Logger logger = LoggerFactory.getLogger(SaswatController.class);

	@Autowired
	SaswatService saswatService;

	@PostMapping(value = "/singlesms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SaswatStatus> getValidationStatus(@RequestBody SaswatDto saswatDto) {
		logger.info("Validation saswatDto {}", JsonUtils.convertObjectToJsonString(saswatDto));
		if (!saswatDto.toString().isEmpty()) {
			return saswatService.selectSmsSaswat(saswatDto);
		}
		// return new ResponseEntity<>(SaswatErrorResponse.returnBadRequest(),
		// HttpStatus.BAD_REQUEST);
		return new ResponseEntity(SaswatErrorResponse.returnBadRequest(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/otp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SaswatStatus> getSaswatOtp(@RequestBody SaswatDto saswatDto) {
		logger.info("Validation saswatDto {}", JsonUtils.convertObjectToJsonString(saswatDto));
		if (!saswatDto.toString().isEmpty()) {
			return saswatService.selectSmsSaswat(saswatDto);
		}
		return new ResponseEntity(SaswatErrorResponse.returnBadRequest(), HttpStatus.BAD_REQUEST);
	}

}
