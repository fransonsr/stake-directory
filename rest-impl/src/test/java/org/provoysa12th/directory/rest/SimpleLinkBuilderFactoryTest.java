package org.provoysa12th.directory.rest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class SimpleLinkBuilderFactoryTest {

	@Test
	public void testFactory() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/server"));

		LinkBuilderFactory test = new SimpleLinkBuilderFactory(request);
		LinkBuilder builder = test.newBuilder();
		assertThat(builder, is(notNullValue()));
		assertThat(builder.build().getHref(), is("http://localhost:8080/server"));
	}
}
