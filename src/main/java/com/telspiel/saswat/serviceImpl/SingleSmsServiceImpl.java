package com.telspiel.saswat.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telspiel.saswat.error.response.SaswatErrorResponse;
import com.telspiel.saswat.service.SaswatApiService;
import com.telspiel.saswat.service.SingleSmsService;
import com.telspiel.saswat.utils.PropertiesConfig;
import com.telspiel.saswat.utils.SaswatDto;
import com.telspiel.saswat.utils.SaswatStatus;

@Service
public class SingleSmsServiceImpl implements SingleSmsService {

	private static Logger logger = LoggerFactory.getLogger(SingleSmsServiceImpl.class);

	@Autowired
	PropertiesConfig propertiesConfig;
	@Autowired
	SaswatApiService saswatApiService;

	@Override
	public SaswatStatus sendSingleSms(SaswatDto saswatDto) {

		SaswatStatus saswatStatus = new SaswatStatus();
		long startTime = System.currentTimeMillis();

		try {
			String baseURL = propertiesConfig.getSingleBaseURL();
			String username = "SwatApi"; /* propertiesConfig.getUsername(); */
			String dest = saswatDto.getDest();
			String apikey = propertiesConfig.getApikey();
			String signature = propertiesConfig.getSignature();
			String msgtype = propertiesConfig.getMsgtype();
			String entityid = propertiesConfig.getEntityid();
			String templateid = propertiesConfig.getTemplateid();
			String name = saswatDto.getName();
			String msgtxt = "Dear%20" + name
					+ "%2C%20Welcome%20to%20Saswat.%20Your%20application%20ID%20%3A%20A0932123%20and%20customer%20ID%20%3A%20CID0012001.%20If%20you%20have%20any%20questions%20reach-out%20to%20your%20sales%20officer.%20-Saswat%20Finance";

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

			String jsonString = response.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(jsonString);
			// Store data into a list of key-value pairs
			List<Map.Entry<String, String>> dataList = new ArrayList<>();
			Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();
			while (fieldsIterator.hasNext()) {
				Map.Entry<String, JsonNode> field = fieldsIterator.next();
				dataList.add(Map.entry(field.getKey(), field.getValue().asText()));
			}

			Entry<String, String> scode = dataList.get(0);
			Entry<String, String> sdesc = dataList.get(1);

			String code = scode.getValue();
			String desc = sdesc.getValue();
			if (code.equalsIgnoreCase("6001")) {
				saswatStatus.setStatus("SUCCESS");
			} else {
				saswatStatus.setStatus("FAILURE");
			}
			saswatStatus.setCode(code);
			saswatStatus.setDesc(desc);
			logger.info("single sms service response packet: " + saswatStatus);
			// saswatApiService.saveSaswatApiLogs(saswatDto, fullURL, saswatStatus,
			// startTime);

			return saswatStatus;
		} catch (Exception e) {
			saswatStatus = SaswatErrorResponse.returnInternalServerErrorResponse(e);
		}
		return saswatStatus;
	}

}
