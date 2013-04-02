package org.provoysa12th.directory.service;

import java.util.UUID;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public Organization findById(Long id) {
		return organizationRepository.findOne(id);
	}

	@Override
	public Organization createOrUpdate(Organization organization) {
		if(organization.getNodeId() == null) {
			organization.setUuid(UUID.randomUUID());
		}

		return organizationRepository.save(organization);
	}


}
