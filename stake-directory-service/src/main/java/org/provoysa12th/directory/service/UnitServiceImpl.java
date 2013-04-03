package org.provoysa12th.directory.service;

import java.util.UUID;

import javax.annotation.PostConstruct;

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

	@Autowired
	UnitRepository unitRepository;
	BaseServiceHelper<Unit> helper;

	@Autowired
	OrganizationService organizationService;

	@PostConstruct
	public void init() {
		helper = new BaseServiceHelper<Unit>(unitRepository);
	}

	@Override
	public Unit findById(Long id) {
		return helper.findById(id);
	}

	@Override
	public Unit findByUUID(UUID uuid) {
		return helper.findByUUID(uuid);
	}

	@Override
	public Unit createOrUpdate(Unit entity) {
		return helper.createOrUpdate(entity);
	}

	@Override
	public Unit findByUnitNumber(Integer unitNumber) {
		return unitRepository.findByPropertyValue("unitNumber", unitNumber);
	}

	@Override
	public void addOrganization(Unit unit, Organization organization) {
		organization = organizationService.createOrUpdate(organization);

		unit.getUnitOrganizations().add(new UnitOrganization(unit, organization));
		organization.setUnit(unit);

		unitRepository.save(unit);
	}
}
