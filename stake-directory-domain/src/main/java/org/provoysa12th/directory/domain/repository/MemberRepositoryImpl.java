package org.provoysa12th.directory.domain.repository;

import javax.annotation.PostConstruct;

import org.provoysa12th.directory.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRepositoryImpl extends BaseRepositoryImpl<Member> {

	@Autowired
	MemberRepository baseRepository;
	BaseRepositoryExtensionHelper<Member> helper;

	@PostConstruct
	public void init() {
		helper = new BaseRepositoryExtensionHelper<Member>(neo4jTemplate, baseRepository);
	}

	@Override
	public Member saveEntity(Member entity) {
		return helper.saveEntity(entity);
	}

}
