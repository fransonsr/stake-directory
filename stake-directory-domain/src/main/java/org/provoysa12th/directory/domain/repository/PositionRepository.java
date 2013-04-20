package org.provoysa12th.directory.domain.repository;

import java.util.List;
import java.util.UUID;

import org.provoysa12th.directory.domain.Position;
import org.provoysa12th.directory.domain.Position.Type;

public interface PositionRepository extends BaseRepository<Position> {

	Position findByUuid(UUID uuid);

	List<Position> findAllByName(String name);

	List<Position> findAllByNameLike(String regex);

	List<Position> findAllByType(Type type);


}
