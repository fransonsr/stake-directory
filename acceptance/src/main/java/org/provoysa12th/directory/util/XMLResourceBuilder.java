package org.provoysa12th.directory.util;

import javax.ws.rs.core.MediaType;

public interface XMLResourceBuilder {

	XMLResource build();

	XMLResourceBuilder accept(MediaType type);

	XMLResourceBuilder accept(String mediaType);

	XMLResourceBuilder authorization(String realm, String credential);

}