package org.provoysa12th.directory.domain.repository;

import org.provoysa12th.directory.domain.BaseEntity;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface BaseRepository<T extends BaseEntity> extends GraphRepository<T>, BaseRepositoryExtension<T> {

}
