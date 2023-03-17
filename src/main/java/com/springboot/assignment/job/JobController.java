package com.springboot.assignment.job;

import java.text.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobController {
	private final JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}
	
	@GetMapping("/jobs.html")
	public String all(Model model) {
		model.addAttribute("jobs", jobService.findAll());
		return "job/list";
	}
	
	@GetMapping("/create-job.html")
	public String createJob(Model model) throws ParseException{
		model.addAttribute("job", new Job(null, null, null, null, 0));
		return "job/create-job";
	}
	
	@GetMapping(value = "/jobs.html", params = "jobTitle")
	public String get(@RequestParam("jobTitle") String jobTitle, Model model) {
		jobService.find(jobTitle)
						.ifPresent(job -> model.addAttribute("job", job));

		return "job/details";
	}
	
	@PostMapping(value = "updateJob")
	public String update(@ModelAttribute Job job , Model model) {
		
		jobService.update(job.getJobTitle(), job);

		model.addAttribute("jobs", jobService.findAll());
		return "job/list";
	}
	
	@PostMapping(value = "createJob")
	public String create(@ModelAttribute("job") Job job , Model model) {
		
		jobService.create(job);

		model.addAttribute("jobs", jobService.findAll());
		return "job/list";
	}
}
