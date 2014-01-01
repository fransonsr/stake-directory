package org.provoysa12th.directory.service;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.OrganizationPosition;
import org.provoysa12th.directory.domain.Position;

public interface OrganizationService extends BaseService<Organization> {

	OrganizationPosition addPosition(Organization organization, Position position);

	OrganizationPosition addPosition(Organization organization, Position position, boolean presiding, int orderIndex);

}
