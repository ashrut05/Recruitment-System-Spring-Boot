package com.springboot.assignment.organization;

import java.util.Optional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;


@Service
public class InMemoryOrganizationService implements OrganizationService{
	
	private final Map<String, Organization> organizations = new ConcurrentHashMap<>();
	
	@Override
	public Iterable<Organization> findAll() {
		return organizations.values();
	}

	@Override
	public Organization create(Organization organization) {
		organizations.put(organization.getOrgName(), organization);
		return organization;
	}

	@Override
	public Optional<Organization> find(String orgName) {
		return Optional.ofNullable(organizations.get(orgName));
	}

	@Override
	public void deleteByOrganization(String orgName) {
		organizations.remove(orgName);
		
	}

	@Override
	public Optional<Organization> update(String orgName, Organization organization) {
		organizations.replace(orgName, organization);
		
		return Optional.ofNullable(organization);
	}
	
}
