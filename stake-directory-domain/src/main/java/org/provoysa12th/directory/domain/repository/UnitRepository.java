package org.provoysa12th.directory.domain.repository;

import java.util.List;
import java.util.UUID;

import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.Unit.Type;

public interface UnitRepository extends BaseRepository<Unit> {

	Unit findByUuid(UUID uuid);

	Unit findByUnitNumber(Integer unitNumber);

	List<Unit> findByName(String name);

	List<Unit> findByNameLike(String regex);

	List<Unit> findByType(Type type);
}
