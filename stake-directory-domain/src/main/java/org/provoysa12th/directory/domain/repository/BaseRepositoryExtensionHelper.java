package org.provoysa12th.directory.domain.repository;

import java.util.UUID;

import org.provoysa12th.directory.domain.BaseEntity;
import org.springframework.data.neo4j.repository.GraphRepository;

public class BaseRepositoryExtensionHelper<T extends BaseEntity> implements BaseRepositoryExtension<T> {

	private final GraphRepository<T> baseRepository;

	public BaseRepositoryExtensionHelper(GraphRepository<T> baseRepository) {
		this.baseRepository = baseRepository;
	}

	@Override
	public T saveEntity(T entity) {
		if(entity.getNodeId() == null) {
			entity.setUuid(UUID.randomUUID());
		}

		return baseRepository.save(entity);
	}
}
