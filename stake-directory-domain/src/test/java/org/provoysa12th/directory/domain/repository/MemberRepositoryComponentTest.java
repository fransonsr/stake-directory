package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {RepositoryComponentTestConfiguration.class})
@TransactionConfiguration
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberRepositoryComponentTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	public void testSaveEntity_create() throws Exception {
		Member member = new Member();

		Member actual = memberRepository.saveEntity(member);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}

	@Test
	public void testSaveEntity_update() throws Exception {
		Member member = new Member();
		member.setFirstName("created first name");

		member = memberRepository.saveEntity(member);
		UUID uuid = member.getUuid();

		assertThat(member, is(notNullValue()));
		assertThat(uuid, is(notNullValue()));

		member.setFirstName("updated");

		Member actual = memberRepository.saveEntity(member);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(equalTo(uuid)));
	}
}
