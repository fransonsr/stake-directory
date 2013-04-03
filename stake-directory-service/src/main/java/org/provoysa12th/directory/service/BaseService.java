package org.provoysa12th.directory.service;

import java.util.UUID;

import org.provoysa12th.directory.domain.BaseEntity;

public interface BaseService<T extends BaseEntity> {

	T findById(Long id);

	T findByUUID(UUID uuid);

	T createOrUpdate(T entity);
}
