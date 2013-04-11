package org.provoysa12th.directory.service;

import java.util.List;
import java.util.UUID;

import org.provoysa12th.directory.domain.BaseEntity;
import org.provoysa12th.directory.domain.repository.BaseRepository;

public class BaseServiceHelper<T extends BaseEntity> implements BaseService<T> {

	private final BaseRepository<T> baseRepository;

	public BaseServiceHelper(BaseRepository<T> baseRepository) {
		this.baseRepository = baseRepository;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return baseRepository.findAll().as(List.class);
	}

	@Override
	public T findById(Long id) {
		return baseRepository.findOne(id);
	}

	@Override
	public T findByUUID(UUID uuid) {
		return baseRepository.findByPropertyValue("uuid", uuid);
	}

	@Override
	public T createOrUpdate(T entity) {
		return baseRepository.saveEntity(entity);
	}

	@Override
	public <E> E load(E entity) {
		return baseRepository.neo4JTemplate().fetch(entity);
	}

}
