package com.telspiel.saswat.service;

import org.springframework.http.ResponseEntity;

import com.telspiel.saswat.utils.SaswatDto;
import com.telspiel.saswat.utils.SaswatStatus;

public interface SaswatService {
	
	ResponseEntity<SaswatStatus> selectSmsSaswat(SaswatDto saswatDto);

}
