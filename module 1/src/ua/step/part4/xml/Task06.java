package ua.step.part4.xml;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * Поиск по XML с использованием XPath
 *
 */
public class Task06 {
	public static void main(String[] args) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse("output2.xml");
			XPathFactory pathFactory = XPathFactory.newInstance();
			XPath xpath = pathFactory.newXPath();
			XPathExpression expr = xpath.compile("country/city/street[not(@WGS_LATITUDE)]");

			NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		    for (int i = nodes.getLength() - 1; i >= 0; i--) 
		    {
		    	System.out.println(nodes.item(i).getAttributes().getNamedItem("ID"));
		    	nodes.item(i).getParentNode().removeChild(nodes.item(i));
		    }
			try {
				Transformer tr = TransformerFactory.newInstance().newTransformer();
				DOMSource source = new DOMSource(document);
				FileOutputStream fos = new FileOutputStream("D:\\temp\\truffels\\Japan.xml");
				StreamResult result = new StreamResult(fos);
				tr.transform(source, result);
			} catch (TransformerException | IOException e) {
				e.printStackTrace(System.out);
			}
			
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int deleteUser = 0;
		for (int i = 0; i < 5; i++) {
			deleteUser = i;
		}
		System.out.println(deleteUser);
	}
}