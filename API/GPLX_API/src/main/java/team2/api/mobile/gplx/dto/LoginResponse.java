package team2.api.mobile.gplx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
	
	@JsonProperty("Id")
	private String id;
	
	@JsonProperty("Username")
	private String username;
	
	@JsonProperty("Email")
	private String email;

	@JsonProperty("RoleId")
	private String roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
