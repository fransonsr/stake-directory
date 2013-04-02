package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.ImpermanentGraphDatabase;
import org.provoysa12th.directory.domain.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {UnitRepositoryComponentTest.ComponentConfiguration.class})
@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UnitRepositoryComponentTest {

	@Configuration
	@EnableNeo4jRepositories(basePackages = {"org.provoysa12th.directory.domain.repository"})
	public static class ComponentConfiguration extends Neo4jConfiguration {
		@Bean
		public GraphDatabaseService graphDatabaseService() {
			return new ImpermanentGraphDatabase();
		}
	}

	@Autowired
	UnitRepository unitRepository;

	@Test
	public void testSave() throws Exception {
		Unit unit = new Unit();
		Unit savedUnit = unitRepository.save(unit);

		Unit actual = unitRepository.findOne(savedUnit.getNodeId());
		assertThat(actual, is(notNullValue()));
	}

}
