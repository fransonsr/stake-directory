package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.provoysa12th.directory.domain.Member;

public class MemberRepositoryImplTest {

	private MemberRepository baseRepository;
	private MemberRepositoryImpl repository;

	@Before
	public void setup() {
		baseRepository = mock(MemberRepository.class);
		repository = new MemberRepositoryImpl();
		repository.baseRepository = baseRepository;
		repository.init();
	}

	@Test
	public void testSaveEntity_create() throws Exception {
		Member member = new Member();

		when(baseRepository.save(member)).thenReturn(member);

		Member actual = repository.saveEntity(member);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}
}
