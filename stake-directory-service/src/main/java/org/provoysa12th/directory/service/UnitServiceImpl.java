package org.provoysa12th.directory.service;

import java.util.Set;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.UnitOrganization;
import org.provoysa12th.directory.domain.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UnitServiceImpl implements UnitService {

	private static class UnitAccessor extends Unit {

		private Unit delegate;

		public UnitAccessor(Unit unit) {
			this.delegate = unit;
		}

		@Override
		public Set<UnitOrganization> getOrganizations() {
			return delegate.getOrganizations();
		}

		@Override
		public void setOrganizations(Set<UnitOrganization> organizations) {
			delegate.setOrganizations(organizations);
		}
	}

	@Autowired
	UnitRepository unitRepository;

	@Autowired
	OrganizationService organizationService;

	@Override
	public Unit findById(Long id) {
		return unitRepository.findOne(id);
	}

	@Override
	public Unit findByUnitNumber(Integer unitNumber) {
		return unitRepository.findByPropertyValue("unitNumber", unitNumber);
	}

	@Override
	public Unit createOrUpdate(Unit unit) {
		return unitRepository.save(unit);
	}

	@Override
	public void addOrganization(Unit unit, Organization organization) {
		organization = organizationService.createOrUpdate(organization);

		UnitAccessor unitAccessor = new UnitAccessor(unit);
		unitAccessor.getOrganizations().add(new UnitOrganization(unit, organization));
		organization.setUnit(unit);

		unitRepository.save(unit);
	}

}
