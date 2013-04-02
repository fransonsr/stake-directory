package org.provoysa12th.directory.service;

import java.util.Set;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Unit;

public interface UnitService {

	Unit findById(Long id);

	Unit findByUnitNumber(Integer unitNumber);

	Unit createOrUpdate(Unit unit);

	Set<Organization> getOrganizations(Unit unit);

	void addOrganization(Unit unit, Organization organization);

}