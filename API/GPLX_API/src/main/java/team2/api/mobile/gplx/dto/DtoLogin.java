package team2.api.mobile.gplx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoLogin {
	@JsonProperty("LoginType")
	private String loginType;
	@JsonProperty("Password")
	private String password;
	
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
