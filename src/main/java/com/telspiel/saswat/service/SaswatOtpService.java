package com.telspiel.saswat.service;

import com.telspiel.saswat.utils.SaswatDto;
import com.telspiel.saswat.utils.SaswatStatus;

public interface SaswatOtpService {
	
	SaswatStatus sendSaswatOtp(SaswatDto saswatDto);

}
