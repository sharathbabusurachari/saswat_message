package com.telspiel.saswat.service;

import com.telspiel.saswat.utils.SaswatDto;
import com.telspiel.saswat.utils.SaswatStatus;

public interface SaswatApiService {
	
	public void saveSaswatApiLogs(SaswatDto saswatDto, String httpRequest, SaswatStatus saswatStatus, long startTime);

}
