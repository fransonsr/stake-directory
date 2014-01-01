package org.provoysa12th.directory.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.provoysa12th.directory.domain.Position;
import org.provoysa12th.directory.domain.repository.PositionRepository;
import org.provoysa12th.directory.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionRepository baseRepository;

	BaseServiceHelper<Position> helper;

	@PostConstruct
	public void init() {
		helper = new BaseServiceHelper<Position>(baseRepository);
	}

	@Override
	public List<Position> findAll() {
		return helper.findAll();
	}

	@Override
	public Position findById(Long id) {
		return helper.findById(id);
	}

	@Override
	public Position findByUUID(UUID uuid) {
		return helper.findByUUID(uuid);
	}

	@Override
	public Position createOrUpdate(Position entity) {
		return helper.createOrUpdate(entity);
	}

	@Override
	public <E> E load(E entity) {
		return helper.load(entity);
	}

}
