package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {RepositoryComponentTestConfiguration.class})
@TransactionConfiguration
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class OrganizationRepositoryComponentTest {

	@Autowired
	OrganizationRepository organizationRepository;

	@Test
	public void testSave() throws Exception {
		Organization org = new Organization();
		Organization savedOrg = organizationRepository.save(org);

		Organization actual = organizationRepository.findOne(savedOrg.getNodeId());
		assertThat(actual, is(notNullValue()));
	}
}
