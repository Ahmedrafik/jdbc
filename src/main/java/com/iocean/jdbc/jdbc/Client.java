package com.iocean.jdbc.jdbc;

public class Client {

	private String name;
	private String firstname;
	private Gender gender;
	
	public Client(String name, String firstname, Gender gender) {
		this.name = name;
		this.firstname = firstname;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", firstname=" + firstname + ", gender=" + gender + "]";
	}
	
	
}
