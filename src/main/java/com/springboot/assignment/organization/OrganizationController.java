package com.springboot.assignment.organization;

import java.text.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrganizationController {
	private final OrganizationService organizationService;
	
	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	@GetMapping("/organizations.html")
	public String all(Model model) {
		model.addAttribute("organizations", organizationService.findAll());
		return "organizations/list";
	}
	
	@GetMapping("/create-organization.html")
	public String createOrganization(Model model) throws ParseException{
		model.addAttribute("organization", new Organization(null, 0, null, null, null));
		return "organizations/create-organization";
	}
	
	@GetMapping(value = "/organizations.html", params = "orgName")
	public String get(@RequestParam("orgName") String orgName, Model model) {
		organizationService.find(orgName)
						.ifPresent(organization -> model.addAttribute("organization", organization));

		return "organizations/details";
	}
	
	@PostMapping(value = "updateOrganization")
	public String update(@ModelAttribute Organization organization , Model model) {
		
		organizationService.update(organization.getOrgName(), organization);

		model.addAttribute("organizations", organizationService.findAll());
		return "organizations/list";
	}
	
	@PostMapping(value = "createOrganization")
	public String create(@ModelAttribute("organization") Organization organization , Model model) {
		
		organizationService.create(organization);

		model.addAttribute("organizations", organizationService.findAll());
		return "organizations/list";
	}
}
