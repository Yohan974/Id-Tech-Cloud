package com.idtech.cloud;

public class Customer {

	private int customerID;
	private String name;
	private String contactFirstname;
	private String contactLastname;
	private String contactCity;
	private String contactAddress;
	private String contactPhone;
	private String contactEmail;
	
	public Customer() {
		
	}
	
	public Customer(int customerID, String name, String contactFirstname, String contactLastname, String contactCity, String contactAddress, String contactPhone, String contactEmail) {
		this.setCustomerID(customerID);
		this.setName(name);
		this.setContactFirstname(contactFirstname);
		this.setContactLastname(contactLastname);
		this.setContactCity(contactCity);
		this.setContactAddress(contactAddress);
		this.setContactPhone(contactPhone);
		this.setContactEmail(contactEmail);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getContactFirstname() {
		return contactFirstname;
	}

	public void setContactFirstname(String contactFirstname) {
		this.contactFirstname = contactFirstname;
	}

	public String getContactLastname() {
		return contactLastname;
	}

	public void setContactLastname(String contactLastname) {
		this.contactLastname = contactLastname;
	}

	public String getContactCity() {
		return contactCity;
	}

	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
}
