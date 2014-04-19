package br.com.usp.ime.ombudsmanadm.model.vo;

import java.io.Serializable;

public class User implements Serializable {
	private String uspNumber;
	private String email;
	private String name;
	
	public String getUspNumber() {
		return uspNumber;
	}
	public void setUspNumber(String uspNumber) {
		this.uspNumber = uspNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}