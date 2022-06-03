package team2.api.mobile.gplx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignupDto {
	@JsonProperty("Email")
	private String email;
	@JsonProperty("Username")
	private String username;
	@JsonProperty("Password")
	private String password;
	@JsonProperty("Firstname")
	private String firstName;
	@JsonProperty("Lastname")
	private String lastName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
