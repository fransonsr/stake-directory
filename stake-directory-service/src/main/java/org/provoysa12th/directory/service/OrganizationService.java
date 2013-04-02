package org.provoysa12th.directory.service;

import org.provoysa12th.directory.domain.Organization;

public interface OrganizationService {

	Organization createOrUpdate(Organization organization);

	public abstract Organization findById(Long id);

}
