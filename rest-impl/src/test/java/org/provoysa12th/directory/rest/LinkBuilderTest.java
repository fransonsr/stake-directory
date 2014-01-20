package org.provoysa12th.directory.rest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.junit.Test;
import org.provoysa12th.directory.rest.model.Link;

public class LinkBuilderTest {

	@Path("/path")
	static class TestClass {

		@GET
		@Path("/entity/{entityid}")
		public String read(@PathParam("entityid") String entityid) {

			return entityid;
		}

		@DELETE
		@Path("/entity/{entityid}")
		public void delete(@PathParam("entityid") String entityid) {

		}
	}

	@Test
	public void testBuild() throws Exception {
		LinkBuilder test = new LinkBuilder();
		Link link = test.baseURI("http://localhost")
						.path(TestClass.class)
						.path(TestClass.class, "read")
						.rel("relation")
						.type("application/xml")
						.title("Link for entity")
						.method(TestClass.class, "read")
						.method(TestClass.class, "delete")
						.build("ENTITYID");

		assertThat(link, is(notNullValue()));
		assertThat(link.getHref(), is("http://localhost/path/entity/ENTITYID"));
		assertThat(link.getRel(), is("relation"));
		assertThat(link.getType(), is("application/xml"));
		assertThat(link.getTitle(), is("Link for entity"));
		assertThat(link.getMethod(), containsString("GET"));
		assertThat(link.getMethod(), containsString("DELETE"));
	}

	@Test
	public void testBuild_noMethods() throws Exception {
		LinkBuilder test = new LinkBuilder();
		String relation = "relation";
		Link link = test.baseURI("http://localhost")
						.path(TestClass.class)
						.path(TestClass.class, "read")
						.rel(relation)
						.build("ENTITYID");

		assertThat(link, is(notNullValue()));
		assertThat(link.getMethod(), is(nullValue()));
	}

}
