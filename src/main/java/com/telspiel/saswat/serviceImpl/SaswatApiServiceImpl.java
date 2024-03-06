package com.telspiel.saswat.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.telspiel.saswat.repository.SaswatApiLogsDao;
import com.telspiel.saswat.service.SaswatApiService;
import com.telspiel.saswat.utils.PropertiesConfig;
import com.telspiel.saswat.utils.SaswatApiLogs;
import com.telspiel.saswat.utils.SaswatDto;
import com.telspiel.saswat.utils.SaswatStatus;

@Service
public class SaswatApiServiceImpl implements SaswatApiService {

	@Autowired
	PropertiesConfig propertiesConfig;
	@Autowired
	SaswatApiLogsDao saswatApiLogsDao;

	@Override
	public void saveSaswatApiLogs(SaswatDto saswatDto, String httpRequest, SaswatStatus saswatStatus, long startTime) {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(saswatDto);
			SaswatApiLogs saswatApiLogs = gson.fromJson(jsonString, SaswatApiLogs.class);
			saswatApiLogs.setOtp(saswatDto.getOtp());
			saswatApiLogs.setDest(saswatDto.getDest());
			saswatApiLogsDao.save(saswatApiLogs);

		} catch (Exception e) {

		}

	}

}
