package org.provoysa12th.directory.service;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.UnitOrganization;

public interface UnitService extends BaseService<Unit> {

	Unit findByUnitNumber(Integer unitNumber);

	UnitOrganization addOrganization(Unit unit, Organization organization);

	UnitOrganization addOrganization(Unit unit, Organization organization, boolean presiding, int orderIndex);


}