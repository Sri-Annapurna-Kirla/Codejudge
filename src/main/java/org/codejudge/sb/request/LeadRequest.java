package org.codejudge.sb.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeadRequest {
	@NotNull(message = "Fields missing")
	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	@NotNull(message = "Fields missing")
	private String lastName;

	@NotNull(message = "Fields missing")
	@Size(min =10,max=10)
	private String mobile;

	@NotNull(message = "Fields missing")
	private String email;

	@JsonProperty("location_type")
	@NotNull(message = "Fields missing")
	private String locationType;

	@JsonProperty("location_string")
	@NotNull(message = "Fields missing")
	private String locationString;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocationString() {
		return locationString;
	}

	public void setLocationString(String locationString) {
		this.locationString = locationString;
	}

}
