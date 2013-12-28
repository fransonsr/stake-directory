package org.provoysa12th.directory.domain.repository;

import org.provoysa12th.directory.domain.BaseEntity;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public interface BaseRepositoryExtension<T extends BaseEntity> {

	Neo4jTemplate neo4JTemplate();

	T saveEntity(T entity);

}
