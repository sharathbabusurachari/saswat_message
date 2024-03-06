package com.telspiel.saswat.utils;

public class SaswatStatus {

	private String status;
	private String code;
	private String desc;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "SaswatStatus [status=" + status + ", code=" + code + ", desc=" + desc + "]";
	}

}
