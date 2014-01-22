package org.provoysa12th.directory.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.StringUtils;
import org.provoysa12th.directory.rest.model.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(LinkBuilder.class);

	private static final Set<Class<? extends Annotation>> METHODS =
			new HashSet<>(Arrays.asList(GET.class, POST.class, PUT.class, DELETE.class));

	private UriBuilder builder;
	private List<String> methods = new ArrayList<>();
	private String relation;
	private String mediaType;
	private String title;

	public LinkBuilder baseURI(String baseURI) {
		builder = UriBuilder.fromUri(baseURI);
		return this;
	}

	public LinkBuilder path(Class<?> resource) {
		builder.path(resource);
		return this;
	}

	public LinkBuilder path(Class<?> resource, String method) {
		builder.path(resource, method);
		return this;
	}

	public LinkBuilder rel(String relation) {
		this.relation = relation;
		return this;
	}

	public LinkBuilder type(String mediaType) {
		this.mediaType = mediaType;
		return this;
	}

	public LinkBuilder title(String title) {
		this.title = title;
		return this;
	}

	public LinkBuilder method(Class<?> resource, String methodName) {
		Method method = findMethod(resource, methodName);

		if(method != null) {
			for(Annotation annotation : method.getAnnotations()) {
				Class<? extends Annotation> annotationType = annotation.annotationType();
				if(METHODS.contains(annotationType)) {
					methods.add(annotationType.getSimpleName());
				}
			}
		}

		return this;
	}

	private Method findMethod(Class<?> resource, String methodName) {
		Method method = null;
		for(Method m : resource.getMethods()) {
			if(m.getName().equals(methodName)) {
				method = m;
				break;
			}
		}

		if(method == null) {
			LOG.warn(String.format("Unable to find method '%s' in resource: %s", methodName, resource.getCanonicalName()));
		}

		return method;
	}

	public Link build(Object... values) {
		Link link = new Link();
		link.setHref(builder.build(values).toASCIIString());
		link.setRel(relation);
		link.setType(mediaType);
		link.setTitle(title);

		if(!methods.isEmpty()) {
			link.setMethod(StringUtils.join(methods, ","));
		}

		return link;
	}

}
