package br.com.usp.ime.ombudsmanadm.model.vo;

import android.content.ContentValues;

public class Incident {

	private Long id;
	private Long uspNumber;
	private String description;
	private String localization;
	private double latitude;
	private double longitude;
	private byte[] photo;
	private String createdAt;
	private String updatedAt;

	public Incident() {
	}

	public Incident(Long uspNumber, String description) {
		this.uspNumber = uspNumber;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUspNumber() {
		return uspNumber;
	}

	public void setUspNumber(Long uspNumber) {
		this.uspNumber = uspNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}	
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ContentValues toContentValues() {
		ContentValues values = new ContentValues();
		
		values.put("id", this.getId());
		values.put("uspNumber", this.getUspNumber());
		values.put("description", this.getDescription());
		values.put("localization", this.getLocalization());
		values.put("latitude", this.getLatitude());
		values.put("longitude", this.getLongitude());
		values.put("photo", this.getPhoto());
		values.put("createdAt", this.getCreatedAt());
		values.put("updatedAt", this.getUpdatedAt());

		return values;
	}

	public String toString() {
		return uspNumber + " - " + description;
	}
}