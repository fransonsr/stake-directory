package org.provoysa12th.directory.domain.repository;

import org.provoysa12th.directory.domain.BaseEntity;

public interface BaseRepositoryExtension<T extends BaseEntity> {

	T saveEntity(T entity);
}
