package org.provoysa12th.directory.domain.repository;

import org.provoysa12th.directory.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public abstract class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepositoryExtension<T> {

	@Autowired
	protected Neo4jTemplate neo4jTemplate;

	@Override
	public Neo4jTemplate neo4JTemplate() {
		return neo4jTemplate;
	}

}
