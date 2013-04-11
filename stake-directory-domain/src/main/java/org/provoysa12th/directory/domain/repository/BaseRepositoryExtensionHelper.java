package org.provoysa12th.directory.domain.repository;

import java.util.UUID;

import org.provoysa12th.directory.domain.BaseEntity;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class BaseRepositoryExtensionHelper<T extends BaseEntity> implements BaseRepositoryExtension<T> {

	private final Neo4jTemplate neo4JTemplate;
	private final GraphRepository<T> baseRepository;

	public BaseRepositoryExtensionHelper(Neo4jTemplate neo4jTemplate, GraphRepository<T> baseRepository) {
		this.neo4JTemplate = neo4jTemplate;
		this.baseRepository = baseRepository;
	}

	@Override
	public Neo4jTemplate neo4JTemplate() {
		return neo4JTemplate;
	}

	@Override
	public T saveEntity(T entity) {
		if(entity.getNodeId() == null) {
			entity.setUuid(UUID.randomUUID());
		}

		return baseRepository.save(entity);
	}
}
