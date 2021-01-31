package com.idtech.cloud;

public class User {
	
	private int userID;
	private String firstname;
	private String lastname;
	private String email;
	private String login;
	private String password;
	private String role;
	private int customerID;
	
	public User() {
	
	}
	
	public User(int userID, String firstname, String lastname, String email, String login, String password, String role, int customerID) {
		this.setUserID (userID);
		this.setFirstname (firstname);
		this.setLastname (lastname);
		this.setEmail (email);
		this.setLogin (login);
		this.setPassword (password);
		this.setRole (role);
		this.setCustomerID (customerID);
	}
	
	public int getUserID() {
        return userID;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname ) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname ) {
        this.lastname = lastname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email ) {
        this.email = email;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login ) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password ) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role ) {
        this.role = role;
    }
    
    public int getCustomerID() {
        return customerID;
    }
    
    public void setCustomerID(int customerID ) {
        this.customerID = customerID;
    }
    
    public String toString() {
    	return "User[userID=" + userID + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", login=" + login + ", role=" + role + ", customerID=" + customerID +"]"; 
    }
}
