package com.springboot.assignment.candidate;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Candidate{
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String phone;
	private String email;
	
	@JsonCreator
	public Candidate (
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("address") String address,
			@JsonProperty("city") String city,
			@JsonProperty("phone") String phone,
			@JsonProperty("email") String email
	) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Candidate candidate = (Candidate) o;
		return Objects.equals(email, candidate.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public String toString() {
		return String.format(
				"Candidate [First Name=%s, Last Name=%s, Address=%s , City=%s, Phone=%s, Email=%s]",
				this.firstName, this.lastName, this.address, this.city, this.phone, this.email);
	}
}
