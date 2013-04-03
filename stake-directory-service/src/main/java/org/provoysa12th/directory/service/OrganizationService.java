package org.provoysa12th.directory.service;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Position;

public interface OrganizationService extends BaseService<Organization> {

	void addPosition(Organization organization, Position position);

}
