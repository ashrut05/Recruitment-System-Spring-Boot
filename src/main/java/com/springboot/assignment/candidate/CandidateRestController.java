package com.springboot.assignment.candidate;

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

import java.net.URI;

@RestController
@RequestMapping("/candidates")
public class CandidateRestController {
	private final CandidateService candidateService;
	
	public CandidateRestController(CandidateService candidateService){
		this.candidateService = candidateService;
	}
	
	@GetMapping
	public Iterable<Candidate> all() {
		return candidateService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Candidate> create(@RequestBody Candidate candidate, UriComponentsBuilder uriBuilder) {
		Candidate created = candidateService.create(candidate);
		URI newCandidateUri = uriBuilder.path("/candidates/{email}").build(created.getEmail());
		return ResponseEntity
	            .created(newCandidateUri)
	            .body(created);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Candidate> get(@PathVariable("email") String email) {
		return candidateService.find(email)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{email}")
	public void delete(@PathVariable String email) {
		candidateService.deleteByEmail(email);
	}
	
	@PutMapping("/{email}")
	public ResponseEntity<Candidate> update(@PathVariable("email") String email, @RequestBody Candidate candidate) {
		return candidateService.update(email, candidate)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
