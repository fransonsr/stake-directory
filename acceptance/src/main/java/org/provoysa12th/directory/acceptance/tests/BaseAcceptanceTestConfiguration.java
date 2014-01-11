package org.provoysa12th.directory.acceptance.tests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseAcceptanceTestConfiguration {

	@Bean
	public String baseURI(@Value("#{systemProperties['instance.uri']}") String uri) {
		return uri;
	}

	@Bean
	public Client jerseyClient() {
		return ClientBuilder.newClient();
	}
}
