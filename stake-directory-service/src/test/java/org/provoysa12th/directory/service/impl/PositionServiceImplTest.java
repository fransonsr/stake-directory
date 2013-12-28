package org.provoysa12th.directory.service.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.provoysa12th.directory.domain.Position;
import org.provoysa12th.directory.domain.repository.PositionRepository;
import org.provoysa12th.directory.service.impl.PositionServiceImpl;

public class PositionServiceImplTest {

	private PositionServiceImpl service;
	private PositionRepository baseRepository;

	@Before
	public void setup() {
		baseRepository = mock(PositionRepository.class);
		service = new PositionServiceImpl();
		service.baseRepository = baseRepository;
		service.init();
	}

	@Test
	public void testFindById() throws Exception {
		when(baseRepository.findOne(1234L)).thenReturn(new Position());

		Position actual = service.findById(1234L);
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testFindByUUID() throws Exception {
		Position position = new Position();
		position.setUuid(UUID.randomUUID());

		when(baseRepository.findByPropertyValue("uuid", position.getUuid())).thenReturn(position);

		Position actual = service.findByUUID(position.getUuid());
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testCreateOrUpdate() throws Exception {
		Position position = new Position();

		when(baseRepository.saveEntity(position)).thenReturn(position);

		Position actual = service.createOrUpdate(position);
		assertThat(actual, is(notNullValue()));
	}
}
