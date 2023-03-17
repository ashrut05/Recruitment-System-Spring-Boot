package com.springboot.assignment.candidate;

import java.util.Optional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
class InMemoryCandidateService implements CandidateService{
	private final Map<String, Candidate> candidates = new ConcurrentHashMap<>();

	@Override
	public Iterable<Candidate> findAll() {
		return candidates.values();
	}

	@Override
	public Candidate create(Candidate candidate) {
		candidates.put(candidate.getEmail(), candidate);
		return candidate;
	}

	@Override
	public Optional<Candidate> find(String email) {
		return Optional.ofNullable(candidates.get(email));
	}

	@Override
	public void deleteByEmail(String email) {
		candidates.remove(email);
	}

	@Override
	public Optional<Candidate> update(String email, Candidate candidate) {
		candidates.replace(email, candidate);
		
		return Optional.ofNullable(candidate);
	}
}
