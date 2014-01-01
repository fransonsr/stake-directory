package org.provoysa12th.directory.domain.repository;

import java.util.UUID;

import org.provoysa12th.directory.domain.Member;

public interface MemberRepository extends BaseRepository<Member> {

	Member findByUuid(UUID uuid);

}
