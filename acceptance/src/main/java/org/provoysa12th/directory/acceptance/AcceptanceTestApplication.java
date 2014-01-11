package org.provoysa12th.directory.acceptance;

import org.glassfish.jersey.server.ResourceConfig;

public class AcceptanceTestApplication extends ResourceConfig {

	public AcceptanceTestApplication() {
		register(HarnessResources.class);
	}
}
