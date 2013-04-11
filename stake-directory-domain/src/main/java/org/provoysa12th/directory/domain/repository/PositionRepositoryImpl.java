package org.provoysa12th.directory.domain.repository;

import javax.annotation.PostConstruct;

import org.provoysa12th.directory.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;

public class PositionRepositoryImpl extends BaseRepositoryImpl<Position> {

	@Autowired
	PositionRepository baseRepository;
	BaseRepositoryExtensionHelper<Position> helper;

	@PostConstruct
	public void init() {
		helper = new BaseRepositoryExtensionHelper<Position>(neo4jTemplate, baseRepository);
	}

	@Override
	public Position saveEntity(Position entity) {
		return helper.saveEntity(entity);
	}
}
