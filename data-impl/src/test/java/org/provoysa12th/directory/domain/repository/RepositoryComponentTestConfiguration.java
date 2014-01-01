package org.provoysa12th.directory.domain.repository;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@EnableNeo4jRepositories(basePackages = {"org.provoysa12th.directory.domain.repository"})
public class RepositoryComponentTestConfiguration extends Neo4jConfiguration {
	@Bean
	public GraphDatabaseService graphDatabaseService() {
		return new TestGraphDatabaseFactory().newImpermanentDatabase();
	}
}