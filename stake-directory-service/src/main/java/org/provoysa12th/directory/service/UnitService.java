package org.provoysa12th.directory.service;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Unit;

public interface UnitService extends BaseService<Unit> {

	Unit findByUnitNumber(Integer unitNumber);

	void addOrganization(Unit unit, Organization organization);

}