package com.telspiel.saswat.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telspiel.saswat.service.BulkSmsService;
import com.telspiel.saswat.service.SaswatOtpService;
import com.telspiel.saswat.service.SaswatService;
import com.telspiel.saswat.service.SingleSmsService;
import com.telspiel.saswat.utils.SaswatDto;
import com.telspiel.saswat.utils.SaswatStatus;

@Service
public class SaswatServiceImpl implements SaswatService {

	private static Logger logger = LoggerFactory.getLogger(SaswatServiceImpl.class);
	
	@Autowired
	SingleSmsService singleSmsService;
	
	@Autowired
	BulkSmsService bulkSmsService;
	
	@Autowired
	SaswatOtpService saswatOtpService;

	public ResponseEntity<SaswatStatus> selectSmsSaswat(SaswatDto saswatDto) {
		try {
			String ApiName = saswatDto.getMsgName();

			switch (ApiName) {
			
			case "OTP":
			case "Otp":
			case "otp":
				
				SaswatStatus otpStatus = saswatOtpService.sendSaswatOtp(saswatDto);
				return new ResponseEntity<>(otpStatus, HttpStatus.OK);

			case "singlesms":
			case "Singlesms":
			case "SingleSms":
			case "SingleSMS":
			case "SINGLESMS":
				
				SaswatStatus singlesmsstatus = singleSmsService.sendSingleSms(saswatDto);
				return new ResponseEntity<>(singlesmsstatus, HttpStatus.OK);

			case "bulksms":
			case "Bulksms":
			case "BulkSms":
			case "BulkSMS":
			case "BULKSMS":
				
				SaswatStatus bulksmsstatus = bulkSmsService.sendBulkSms(saswatDto);
				return new ResponseEntity<>(bulksmsstatus, HttpStatus.OK);
			}

		} catch (Exception e) {

		}
		return null;
	}

}
