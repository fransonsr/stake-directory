package org.provoysa12th.directory.rest.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Variant;

import org.junit.Test;
import org.provoysa12yh.directory.rest.WellknownResources;

import com.google.common.net.HttpHeaders;

public class WellknownResourcesImplTest {

	@Test
	public void testHeartbeat() throws Exception {
		WellknownResources test = new WellknownResourcesImpl();
		;
		Response response = test.heartbeat();
		assertThat(response.getStatus(), is(Status.OK.getStatusCode()));
	}

	@Test
	public void testServiceMeta() throws Exception {
		WellknownResourcesImpl test = new WellknownResourcesImpl();

		Variant variant = new Variant(MediaType.APPLICATION_ATOM_XML_TYPE, (String) null, null);

		Request request = mock(Request.class);
		when(request.selectVariant(WellknownResourcesImpl.VARIANTS)).thenReturn(variant);

		test.request = request;

		Response response = test.serviceMeta();
		assertThat(response.getStatus(), is(Status.OK.getStatusCode()));
		assertThat((MediaType) response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE), is(equalTo(MediaType.APPLICATION_ATOM_XML_TYPE)));
		assertThat(response.getHeaders().getFirst(HttpHeaders.CACHE_CONTROL).toString(), containsString("max-age"));
		assertThat(response.getEntity(), is(notNullValue()));
		assertThat((String) response.getHeaders().getFirst(HttpHeaders.VARY), containsString("Accept"));
	}
}
