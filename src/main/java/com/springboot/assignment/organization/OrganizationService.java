package com.springboot.assignment.organization;

import java.util.Optional;


public interface OrganizationService {
	Iterable<Organization> findAll();
	Organization create(Organization organization);
	Optional<Organization> find(String organizationNo);
	void deleteByOrganization(String organizationNo);
	Optional<Organization> update(String organizationNo, Organization organization);
}
