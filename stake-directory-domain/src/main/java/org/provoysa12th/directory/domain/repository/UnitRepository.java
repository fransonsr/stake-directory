package org.provoysa12th.directory.domain.repository;

import java.util.List;
import java.util.UUID;

import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.Unit.Type;

public interface UnitRepository extends BaseRepository<Unit> {

	Unit findByUuid(UUID uuid);

	Unit findByUnitNumber(Integer unitNumber);

	List<Unit> findAllByName(String name);

	List<Unit> findAllByNameLike(String regex);

	List<Unit> findAllByType(Type type);
}
