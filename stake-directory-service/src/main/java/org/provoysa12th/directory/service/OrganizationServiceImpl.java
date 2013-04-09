package org.provoysa12th.directory.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.OrganizationPosition;
import org.provoysa12th.directory.domain.Position;
import org.provoysa12th.directory.domain.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	PositionService positionService;
	BaseServiceHelper<Organization> helper;

	@PostConstruct
	public void init() {
		helper = new BaseServiceHelper<Organization>(organizationRepository);
	}

	@Override
	public List<Organization> findAll() {
		return helper.findAll();
	}

	@Override
	public Organization findById(Long id) {
		return helper.findById(id);
	}

	@Override
	public Organization findByUUID(UUID uuid) {
		return helper.findByUUID(uuid);
	}

	@Override
	public Organization createOrUpdate(Organization entity) {
		return helper.createOrUpdate(entity);
	}

	@Override
	public OrganizationPosition addPosition(Organization organization, Position position) {
		return addPosition(organization, position, false, -1);
	}

	@Override
	public OrganizationPosition addPosition(Organization organization, Position position, boolean presiding, int orderIndex) {
		position = positionService.createOrUpdate(position);

		OrganizationPosition organizationPosition = new OrganizationPosition(organization, position);
		organization.getOrganizationPositions().add(organizationPosition);
		position.setOrganization(organization);

		organizationRepository.saveEntity(organization);

		return organizationPosition;
	}
}
