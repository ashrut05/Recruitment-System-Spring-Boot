package com.springboot.assignment.organization;

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
@RequestMapping("/organizations")
public class OrganizationRestController {
	private final OrganizationService organizationService;
	
	public OrganizationRestController(OrganizationService organizationService){
		this.organizationService = organizationService;
	}
	
	@GetMapping
	public Iterable<Organization> all() {
		return organizationService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Organization> create(@RequestBody Organization organization, UriComponentsBuilder uriBuilder) {
		Organization created = organizationService.create(organization);
		URI newOrganizationUri = uriBuilder.path("/organizations/{orgName}").build(created.getOrgName());
		return ResponseEntity
	            .created(newOrganizationUri)
	            .body(created);
	}
	
	@GetMapping("/{orgName}")
	public ResponseEntity<Organization> get(@PathVariable("orgName") String orgName) {
		return organizationService.find(orgName)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{orgName}")
	public void delete(@PathVariable String orgName) {
		organizationService.deleteByOrganization(orgName);
	}
	
	@PutMapping("/{orgName}")
	public ResponseEntity<Organization> update(@PathVariable("orgName") String orgName, @RequestBody Organization organization) {
		return organizationService.update(orgName, organization)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
