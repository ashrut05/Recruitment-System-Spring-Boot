package com.springboot.assignment.candidate;

import java.util.Optional;

public interface CandidateService {
	Iterable<Candidate> findAll();
	Candidate create(Candidate candidate);
	Optional<Candidate> find(String email);
	void deleteByEmail(String email);
	Optional<Candidate> update(String email, Candidate candidate);
}
