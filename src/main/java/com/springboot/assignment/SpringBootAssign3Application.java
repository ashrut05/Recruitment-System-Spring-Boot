package com.springboot.assignment;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.assignment.organization.Organization;
import com.springboot.assignment.organization.OrganizationService;
import com.springboot.assignment.candidate.Candidate;
import com.springboot.assignment.candidate.CandidateService;
import com.springboot.assignment.job.Job;
import com.springboot.assignment.job.JobService;

@SpringBootApplication
public class SpringBootAssign3Application implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAssign3Application.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
	
	@Bean
	public ApplicationRunner candidatesInitializer(CandidateService candidateService) {
		return args -> {
			candidateService.create(new Candidate("Ash", "Sharma", "890 Brimorton Drive", "Toronto", "647-904-4127", "ashsharma@gmail.com"));
			candidateService.create(new Candidate("John", "Doe", "125 Redpath Ave", "Toronto", "647-904-3909", "johndoe@gmail.com"));
			candidateService.create(new Candidate("Walter", "White", "1430 Queen St East", "Toronto", "647-901-4887", "walterwhite@gmail.com"));
		};
	}
	
	@Bean
	public ApplicationRunner organizationsInitializer(OrganizationService organizationService) {
		return args -> {
			organizationService.create(new Organization("Good Org", 1234, "1 Yonge St", "647-123-4567", "www.goodorg.com"));
			organizationService.create(new Organization("Best Org", 5678, "500 Church St", "647-456-7890", "www.bestorg.com"));
		};
	}
	
	@Bean
	public ApplicationRunner jobsInitializer(JobService jobService) {
		return args -> {
			jobService.create(new Job("Manager", "Fulltime", "Manager of the branch", "MBA", 90000.00));
			jobService.create(new Job("Assistant Manager", "Part-time", "Assistant Manager of the Branch", "BBA", 50000.00));
		};
	}
}
