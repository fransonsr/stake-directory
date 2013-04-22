package org.provoysa12th.directory.domain;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.provoysa12th.directory.domain.exception.InvalidMRNFormatException;

public class MRNHashTest {

	@Test
	public void testHash() throws Exception {
		String mrn = "012-3456-789A";
		String sha1Hash = "74aed5e9deda7a9e47dbb58867e93e4c2859c3c1";

		MRNHash hash = new MRNHash(mrn);
		assertThat(hash.toString(), is(equalTo(sha1Hash)));
	}

	@Test(expected = InvalidMRNFormatException.class)
	public void testHash_invalid() throws Exception {
		String mrn = "invalid";
		new MRNHash(mrn);
	}
}
