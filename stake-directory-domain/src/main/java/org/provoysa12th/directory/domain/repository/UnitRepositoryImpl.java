package org.provoysa12th.directory.domain.repository;

import javax.annotation.PostConstruct;

import org.provoysa12th.directory.domain.Unit;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitRepositoryImpl extends BaseRepositoryImpl<Unit> {

	@Autowired
	UnitRepository baseRepository;
	BaseRepositoryExtensionHelper<Unit> helper;

	@PostConstruct
	public void init() {
		helper = new BaseRepositoryExtensionHelper<Unit>(neo4jTemplate, baseRepository);
	}

	@Override
	public Unit saveEntity(Unit entity) {
		return helper.saveEntity(entity);
	}
}
