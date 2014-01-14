package org.provoysa12th.directory.util;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.xpath.XPathConstants;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLResourceTest {

	private XMLResource test;

	@Before
	public void setup() throws Exception {
		TestClass testClass = new TestClass();
		testClass.setId("test class id");
		testClass.setValue("test");

		JAXBContext ctx = JAXBContext.newInstance(TestClass.class);
		Marshaller marshaller = ctx.createMarshaller();
		StringWriter writer = new StringWriter();
		marshaller.marshal(testClass, writer);

		StringReader reader = new StringReader(writer.toString());

		test = new XMLResource(reader);
	}

	@Test
	public void testRootElement() throws Exception {
		assertThat(test.rootElement(), is(notNullValue()));
	}

	@Test
	public void testEvaluate() throws Exception {
		String xPathExpression = "/testClass/value";
		Node node = (Node) test.evaluate(xPathExpression, XPathConstants.NODE);
		assertThat(node.getNodeName(), is(equalTo("value")));
	}

	@Test
	public void testValue_attribute() throws Exception {
		String xPathExpression = "/testClass/@id";
		assertThat(test.exists(xPathExpression), is(true));
		assertThat(test.value(xPathExpression), is(equalTo("test class id")));
	}

	@Test
	public void testValue_element() throws Exception {
		String xPathExpression = "/testClass/value";
		assertThat(test.exists(xPathExpression), is(true));
		assertThat(test.value(xPathExpression), is(equalTo("test")));
	}

	@Test
	public void testValue_noElement() throws Exception {
		String xPathExpression = "//noElement";
		assertThat(test.exists(xPathExpression), is(false));
		assertThat(test.value(xPathExpression), is(""));
	}

	@Test
	public void testNode() throws Exception {
		String xPathExpression = "/testClass/value";
		Node node = test.node(xPathExpression);
		assertThat(node.getNodeName(), is(equalTo("value")));
	}

	@Test
	public void testList() throws Exception {
		String xPathExpression = "/testClass/value";
		NodeList list = test.list(xPathExpression);
		assertThat(list.getLength(), is(1));
	}

}
