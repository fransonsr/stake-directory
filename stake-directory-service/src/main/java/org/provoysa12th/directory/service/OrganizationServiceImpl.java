package org.provoysa12th.directory.service;

import java.util.UUID;

import javax.annotation.PostConstruct;

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
	BaseServiceHelper<Organization> helper;

	@PostConstruct
	public void init() {
		helper = new BaseServiceHelper<Organization>(organizationRepository);
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
}
