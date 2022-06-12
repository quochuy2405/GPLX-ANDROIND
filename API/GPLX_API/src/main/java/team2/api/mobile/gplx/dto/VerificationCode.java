package team2.api.mobile.gplx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificationCode {
	
	@JsonProperty("code")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public VerificationCode(String code) {
		this.code = code;
	}
	
	public VerificationCode() {}
}
