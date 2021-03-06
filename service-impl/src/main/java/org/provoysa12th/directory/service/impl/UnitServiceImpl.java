package org.provoysa12th.directory.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.UnitOrganization;
import org.provoysa12th.directory.domain.repository.UnitRepository;
import org.provoysa12th.directory.service.OrganizationService;
import org.provoysa12th.directory.service.UnitService;
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
	public List<Unit> findAll() {
		return helper.findAll();
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
	public <E> E load(E entity) {
		return helper.load(entity);
	}

	@Override
	public Unit findByUnitNumber(Integer unitNumber) {
		return unitRepository.findByPropertyValue("unitNumber", unitNumber);
	}

	@Override
	public UnitOrganization addOrganization(Unit unit, Organization organization) {
		return addOrganization(unit, organization, false, -1);
	}

	@Override
	public UnitOrganization addOrganization(Unit unit, Organization organization, boolean presiding, int orderIndex) {
		if(organization.getNodeId() == null) {
			organization = organizationService.createOrUpdate(organization);
		}

		UnitOrganization unitOrganization = new UnitOrganization(unit, organization, presiding, orderIndex);
		unit.getUnitOrganizations().add(unitOrganization);
		organization.setUnitOrganization(unitOrganization);
		organization.setUnit(unit);

		organizationService.createOrUpdate(organization);
		unitRepository.saveEntity(unit);

		return unitOrganization;
	}
}
