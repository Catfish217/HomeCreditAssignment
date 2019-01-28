package models;

public class User {
	
	String id;
	String email;
	String password;
	
	public User(String email) {
		this.email = email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	
	public void setUserId(String userId) {
		this.id = userId;
	}
	public String getUserId() {
		return this.id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}	
}
