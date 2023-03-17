package com.springboot.assignment.job;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/jobs")
public class JobRestController {
	private final JobService jobService;
	
	public JobRestController(JobService jobService){
		this.jobService = jobService;
	}
	
	@GetMapping
	public Iterable<Job> all() {
		return jobService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Job> create(@RequestBody Job job, UriComponentsBuilder uriBuilder) {
		Job created = jobService.create(job);
		URI newJobUri = uriBuilder.path("/jobs/{jobTitle}").build(created.getJobTitle());
		return ResponseEntity
	            .created(newJobUri)
	            .body(created);
	}
	
	@GetMapping("/{jobTitle}")
	public ResponseEntity<Job> get(@PathVariable("jobTitle") String jobTitle) {
		return jobService.find(jobTitle)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{jobTitle}")
	public void delete(@PathVariable String jobTitle) {
		jobService.deleteById(jobTitle);
	}
	
	@PutMapping("/{jobTitle}")
	public ResponseEntity<Job> update(@PathVariable("jobTitle") String jobTitle, @RequestBody Job job) {
		return jobService.update(jobTitle, job )
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
