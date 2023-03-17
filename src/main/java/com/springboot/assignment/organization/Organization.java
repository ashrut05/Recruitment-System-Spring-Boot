package com.springboot.assignment.organization;

import java.text.ParseException;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Organization {
	private String orgName;
	private int orgId;
	private String address;
	private String phone;
	private String website;
	
	@JsonCreator
	public Organization (
			@JsonProperty("orgName") String orgName,
			@JsonProperty("orgId") int orgId,
			@JsonProperty("address") String address,
			@JsonProperty("phone") String phone,
			@JsonProperty("website") String website
	) throws ParseException {
		this.orgName = orgName;
		this.orgId = orgId;
		this.address = address;
		this.phone = phone;
		this.website = website;
	}
	
	public String getOrgName() {
		return orgName;
	}

	public int getOrgId() {
		return orgId;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() { 
		return website;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Organization organization = (Organization) o;
		return Objects.equals(orgName, organization.orgName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orgName);
	}

	@Override
	public String toString() {
		return String.format(
				"Card [Org Name=%s, Org Id=%d, Address=%s, Phone=%s , Website=%s]",
				getOrgName(), getOrgId(), getAddress(), getPhone(), getWebsite());
	}
}
