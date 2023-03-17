package com.springboot.assignment.job;

import java.util.Optional;

public interface JobService {
	Iterable<Job> findAll();
	Job create(Job job);
	Optional<Job> find(String jobTitle);
	void deleteById(String jobTitle);
	Optional<Job> update(String jobTitle, Job job);
}
