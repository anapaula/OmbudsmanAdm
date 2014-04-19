package br.com.usp.ime.ombudsmanadm.model.vo;

import java.io.Serializable;
import java.util.Arrays;

import android.content.ContentValues;

public class Incident implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3605428064510488315L;
	
	private Long id;
	private Long user;
	private String description;
	private String localization;
	private String latitude;
	private String longitude;
	private byte[] photo;
	private String createdAt;
	private String updatedAt;
	private Status status;

	public Incident() {
	}

	public Incident(Long user, String description) {
		this.user = user;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ContentValues toContentValues() {
		ContentValues values = new ContentValues();
		
		values.put("id", this.getId());
		values.put("user", this.getUser());
		values.put("description", this.getDescription());
		values.put("localization", this.getLocalization());
		values.put("latitude", this.getLatitude());
		values.put("longitude", this.getLongitude());
		values.put("photo", this.getPhoto());
		values.put("createdAt", this.getCreatedAt());
		values.put("updatedAt", this.getUpdatedAt());
		values.put("status", this.getStatus().getStatusValue());

		return values;
	}

	@Override
	public String toString() {
		return "Incident [id=" + id + ", user=" + user + ", description="
				+ description + ", localization=" + localization
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", photo=" + Arrays.toString(photo) + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", status=" + status.getStatusValue()
				+ "]";
	}
}