package org.provoysa12th.directory.service;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.ImpermanentGraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@ComponentScan({"org.provoysa12th.directory.service"})
@EnableNeo4jRepositories(basePackages = {"org.provoysa12th.directory.domain.repository"})
public class ServiceComponentTestConfiguration extends Neo4jConfiguration {
	@Bean
	public GraphDatabaseService graphDatabaseService() {
		return new ImpermanentGraphDatabase();
	}
}