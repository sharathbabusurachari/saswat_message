package com.telspiel.saswat.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SaswatDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int otp;
	private String name;
	private String dest;
	private String msgName;
	private String baseURL;
	private String customerId;
	private String applicationId;

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "SaswatDto [otp=" + otp + ", name=" + name + ", dest=" + dest + ", msgName=" + msgName + ", baseURL="
				+ baseURL + ", customerId=" + customerId + ", applicationId=" + applicationId + "]";
	}

}
