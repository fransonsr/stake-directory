package org.provoysa12th.directory.util;

import java.io.InputStream;
import java.io.Reader;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLResource {

	private Element xml;
	private XPath xPath;

	public XMLResource(InputStream stream) {
		this(new InputSource(stream));
	}

	public XMLResource(Reader reader) {
		this(new InputSource(reader));
	}

	public XMLResource(InputSource source) {
		try {

			xml = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder()
					.parse(source)
					.getDocumentElement();

			xPath = XPathFactory.newInstance().newXPath();
		} catch (Exception e) {
			throw new XMLResourceException("Unable to parse source", e);
		}
	}

	public Element rootElement() {
		return xml;
	}

	public Object evaluate(String xPathExpression, QName returnType) {
		try {
			return xPath.evaluate(xPathExpression, xml, returnType);
		} catch (XPathExpressionException e) {
			throw new XMLResourceException(e);
		}
	}

	public boolean exists(String xPathExpression) {
		return (Boolean)evaluate(xPathExpression, XPathConstants.BOOLEAN);
	}

	public String value(String xPathExpression) {
		return (String)evaluate(xPathExpression, XPathConstants.STRING);
	}

	public Node node(String xPathExpression) {
		return (Node)evaluate(xPathExpression, XPathConstants.NODE);
	}

	public NodeList list(String xPathExpression) {
		return (NodeList)evaluate(xPathExpression, XPathConstants.NODESET);
	}

}
