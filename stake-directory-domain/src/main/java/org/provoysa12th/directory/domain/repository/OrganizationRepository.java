package org.provoysa12th.directory.domain.repository;

import org.provoysa12th.directory.domain.Organization;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface OrganizationRepository extends GraphRepository<Organization>, BaseRepositoryExtension<Organization> {

}
