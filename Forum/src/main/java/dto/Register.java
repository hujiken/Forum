package dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import entity.Account;

public class Register {
	@JsonProperty(value="user_name")
	@Size(min = 5, max = 50)
	private String userName;
	
	@Email
	@NotEmpty
	private String email;
	
	@Size(min = 5, max = 50)
	private String password;
	
	public Account convertToAccount() {
		Account account = new Account();
		account.setUserName(this.getUserName());
		account.setEmail(this.getEmail());
		account.setPassword(this.getPassword());
		return account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Register [userName=" + userName + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
