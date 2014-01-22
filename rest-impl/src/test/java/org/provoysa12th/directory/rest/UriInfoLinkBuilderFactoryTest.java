package org.provoysa12th.directory.rest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import org.junit.Test;

public class UriInfoLinkBuilderFactoryTest {

	@Test
	public void testFactory() throws Exception {
		URI uri = new URI("http://localhost:8080/service");

		UriInfo uriInfo = mock(UriInfo.class);
		when(uriInfo.getBaseUri()).thenReturn(uri);

		String baseURI = new UriInfoLinkBuilderFactory(uriInfo)
			.newBuilder()
			.build()
			.getHref();

		assertThat(baseURI, is("http://localhost:8080/service"));
	}
}
