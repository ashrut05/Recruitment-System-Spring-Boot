package com.springboot.assignment.job;

import java.text.ParseException;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
	private String jobTitle;
	private String jobType;
	private String description;
	private String qualification;
	private double salary;
	
	@JsonCreator
	public Job (
			@JsonProperty("jobTitle") String jobTitle,
			@JsonProperty("jobType") String jobType,
			@JsonProperty("description") String description,
			@JsonProperty("qualification") String qualification,
			@JsonProperty("salary") double salary
	) throws ParseException {
		this.jobTitle = jobTitle;
		this.jobType = jobType;
		this.description = description;
		this.qualification = qualification;
		this.salary = salary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getJobType() {
		return jobType;
	}

	public String getDescription() {
		return description;
	}

	public String getQualification() {
		return qualification;
	}

	public double getSalary() {
		return salary;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Job job = (Job) o;
		return Objects.equals(jobTitle, job.getJobTitle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(jobTitle);
	}

	@Override
	public String toString() {
		return String.format(
				"Job [Job Title=%s, Job Type=%s, Description=%s, Qualification=%s, Salary=%f]",
				getJobTitle(), getJobType(), getDescription(), getQualification(), getSalary());
	}
}
