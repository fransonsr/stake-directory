package org.provoysa12th.directory.domain.repository;

import java.util.List;
import java.util.UUID;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Organization.Type;

public interface OrganizationRepository extends BaseRepository<Organization> {

	Organization findByUuid(UUID uuid);

	List<Organization> findAllByName(String name);

	List<Organization> findAllByNameLike(String regex);

	List<Organization> findAllByType(Type type);

}
