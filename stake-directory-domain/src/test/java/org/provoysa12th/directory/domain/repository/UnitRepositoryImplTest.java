package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.provoysa12th.directory.domain.Unit;

public class UnitRepositoryImplTest {

	private UnitRepositoryImpl repository;
	private UnitRepository baseRepository;

	@Before
	public void setup() {
		repository = new UnitRepositoryImpl();
		baseRepository = mock(UnitRepository.class);
		repository.baseRepository = baseRepository;
		repository.init();
	}

	@Test
	public void testSaveEntity_create() throws Exception {
		Unit unit = new Unit();

		when(baseRepository.save(unit)).thenReturn(unit);

		Unit actual = repository.saveEntity(unit);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}
}
