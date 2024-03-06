package com.telspiel.saswat.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telspiel.saswat.error.response.SaswatErrorResponse;
import com.telspiel.saswat.service.SaswatApiService;
import com.telspiel.saswat.service.SaswatOtpService;
import com.telspiel.saswat.utils.PropertiesConfig;
import com.telspiel.saswat.utils.SaswatDto;
import com.telspiel.saswat.utils.SaswatStatus;

@Service
public class SaswatOtpServiceImpl implements SaswatOtpService {

	private static Logger logger = LoggerFactory.getLogger(SaswatOtpServiceImpl.class);

	@Autowired
	PropertiesConfig propertiesConfig;
	@Autowired
	SaswatApiService saswatApiService;

	@Override
	public SaswatStatus sendSaswatOtp(SaswatDto saswatDto) {
		SaswatStatus saswatStatus = new SaswatStatus();
		long startTime = System.currentTimeMillis();
		try {
			String baseURL = propertiesConfig.getOtpBaseUrl();

			String username = "SwatApi";
			String dest = saswatDto.getDest();
			String apikey = propertiesConfig.getApikey();
			String signature = propertiesConfig.getSignature();
			String msgtype = propertiesConfig.getMsgtype();
			String entityid = propertiesConfig.getEntityid();
			String templateid = "1107170781932088080";
			int smsotp = saswatDto.getOtp();
			String msgtxt = "Greetings%20from%20Saswat%20Finance%2C%20your%20otp%20is%20" + smsotp
					+ "%20for%20the%20verification.%20-%20Saswat%20Finance.%20-SASWAT%20Financial%20Services%20Pvt%20LTD";

			// Build the full URL
			String fullURL = String.format(
					"%s?username=%s&dest=%s&apikey=%s&signature=%s&msgtype=%s&msgtxt=%s&entityid=%s&templateid=%s",
					baseURL, username, dest, apikey, signature, msgtype, msgtxt, entityid, templateid);

			URL url = new URL(fullURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Get the response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String json = response.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);
			String code = jsonNode.get(0).get("code").asText();
			String desc = jsonNode.get(0).get("desc").asText();
			if (code.equalsIgnoreCase("6001")) {
				saswatStatus.setStatus("SUCCESS");
			} else {
				saswatStatus.setStatus("FAILURE");
			}
			saswatStatus.setCode(code);
			saswatStatus.setDesc(desc);
			logger.info("otp service response packet: " + saswatStatus);
			saswatApiService.saveSaswatApiLogs(saswatDto, dest, saswatStatus, startTime);

			return saswatStatus;

		} catch (Exception e) {
			saswatStatus = SaswatErrorResponse.returnInternalServerErrorResponse(e);
		}
		return null;
	}

}
