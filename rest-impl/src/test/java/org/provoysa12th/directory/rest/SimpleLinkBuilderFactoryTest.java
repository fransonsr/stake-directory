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
		when(request.getScheme()).thenReturn("http");
		when(request.getServerName()).thenReturn("localhost");
		when(request.getServerPort()).thenReturn(8080);
		when(request.getServletPath()).thenReturn("/server");

		LinkBuilderFactory test = new SimpleLinkBuilderFactory(request);
		LinkBuilder builder = test.newBuilder();
		assertThat(builder, is(notNullValue()));
		assertThat(builder.build().getHref(), is("http://localhost:8080/server"));
	}
}
