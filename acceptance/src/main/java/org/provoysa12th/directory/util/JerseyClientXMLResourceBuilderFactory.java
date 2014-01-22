package org.provoysa12th.directory.util;

import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class JerseyClientXMLResourceBuilderFactory implements XMLResourceBuilderFactory {

	private Client client;

	public JerseyClientXMLResourceBuilderFactory(Client client) {
		this.client = client;
	}

	@Override
	public XMLResourceBuilder newBuilder(String uri) {
		return new JerseyClientBuilder(uri);
	}

	private class JerseyClientBuilder implements XMLResourceBuilder {

		private WebTarget target;
		private Builder request;

		public JerseyClientBuilder(String uri) {
			target = client.target(uri);
			request = target.request();
		}

		public XMLResourceBuilder accept(MediaType type) {
			request.accept(type);
			return this;
		}

		public XMLResourceBuilder accept(String mediaType) {
			request.accept(mediaType);
			return this;
		}

		public XMLResourceBuilder authorization(String realm, String credential) {
			request.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", realm, credential));
			return this;
		}

		@Override
		public XMLResource build() {
			Response response = request.get();

			return new XMLResource((InputStream)response.getEntity());
		}

	}

}
