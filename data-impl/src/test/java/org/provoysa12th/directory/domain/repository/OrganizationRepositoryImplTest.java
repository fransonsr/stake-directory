package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.provoysa12th.directory.domain.Organization;

public class OrganizationRepositoryImplTest {

	private OrganizationRepositoryImpl repository;
	private OrganizationRepository baseRepository;

	@Before
	public void setup() {
		baseRepository = mock(OrganizationRepository.class);
		repository = new OrganizationRepositoryImpl();
		repository.baseRepository = baseRepository;
		repository.init();
	}

	@Test
	public void testSaveEntity_UUID_generated() throws Exception {
		Organization org = new Organization();

		when(baseRepository.save(org)).thenReturn(org);

		Organization actual = repository.saveEntity(org);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}
}
