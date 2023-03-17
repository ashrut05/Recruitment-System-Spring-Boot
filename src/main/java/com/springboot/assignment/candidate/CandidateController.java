package com.springboot.assignment.candidate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CandidateController {
	private final CandidateService candidateService;
	
	public CandidateController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}
	
	@GetMapping("/candidates.html")
	public String all(Model model) {
		model.addAttribute("candidates", candidateService.findAll());
		return "candidates/list";
	}
	
	@GetMapping("/create-candidate.html")
	public String createCandidate(Model model) {
		model.addAttribute("candidate", new Candidate(null, null, null, null, null, null));
		return "candidates/create-candidate";
	}
	
	@GetMapping(value = "/candidates.html", params = "email")
	public String get(@RequestParam("email") String email, Model model) {
		candidateService.find(email)
						.ifPresent(candidate -> model.addAttribute("candidate", candidate));

		return "candidates/details";
	}
	
	@PostMapping(value = "updateCandidate" , params = "email")
	public String update(@RequestParam("email") String email, @ModelAttribute Candidate candidate , Model model) {
		
		candidateService.update(email, candidate);

		model.addAttribute("candidates", candidateService.findAll());
		return "candidates/list";
	}
	
	@PostMapping(value = "createCandidate")
	public String create(@ModelAttribute("candidate") Candidate candidate , Model model) {
		
		candidateService.create(candidate);

		model.addAttribute("candidates", candidateService.findAll());
		return "candidates/list";
	}
}
