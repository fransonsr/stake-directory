package org.provoysa12th.directory.domain.repository;

import javax.annotation.PostConstruct;

import org.provoysa12th.directory.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganizationRepositoryImpl extends BaseRepositoryImpl<Organization> {

	@Autowired
	OrganizationRepository baseRepository;
	BaseRepositoryExtensionHelper<Organization> helper;

	@PostConstruct
	public void init() {
		helper = new BaseRepositoryExtensionHelper<Organization>(neo4jTemplate, baseRepository);
	}

	@Override
	public Organization saveEntity(Organization entity) {
		return helper.saveEntity(entity);
	}
}
