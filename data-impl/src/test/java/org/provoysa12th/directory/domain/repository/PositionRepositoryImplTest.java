package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.provoysa12th.directory.domain.Position;

public class PositionRepositoryImplTest {

	private PositionRepositoryImpl repository;
	private PositionRepository baseRepository;

	@Before
	public void setup() {
		baseRepository = mock(PositionRepository.class);
		repository = new PositionRepositoryImpl();
		repository.baseRepository = baseRepository;
		repository.init();
	}

	@Test
	public void testSaveEntity() throws Exception {
		Position position = new Position();

		when(baseRepository.save(position)).thenReturn(position);

		Position actual = repository.saveEntity(position);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}

}
