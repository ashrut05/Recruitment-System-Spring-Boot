package com.springboot.assignment.job;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class InMemoryJobService implements JobService{
	
	private final Map<String, Job> jobs = new ConcurrentHashMap<>();
	
	@Override
	public Iterable<Job> findAll() {
		return jobs.values();
	}

	@Override
	public Job create(Job job) {
		jobs.put(job.getJobTitle(), job);
		return job;
	}

	@Override
	public Optional<Job> find(String jobTitle) {
		return Optional.ofNullable(jobs.get(jobTitle));
	}

	@Override
	public void deleteById(String jobTitle) {
		jobs.remove(jobTitle);
		
	}

	@Override
	public Optional<Job> update(String jobTitle, Job job) {
		jobs.replace(jobTitle, job);
		
		return Optional.ofNullable(job);
	}
	
}
