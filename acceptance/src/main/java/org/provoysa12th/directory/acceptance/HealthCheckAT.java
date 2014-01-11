package org.provoysa12th.directory.acceptance;

import org.junit.Test;

@AcceptanceTest
public class HealthCheckAT {

	@Test
	public void testName() throws Exception {
		System.out.println("This is a test");
	}

	@Test
	public void testSomethingElse() {
		throw new RuntimeException("Something else");
	}
}
