package ua.step.part4.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * Dom - чтение и разбор документа.
 *
 */
public class Task01 {
	// FIXME выведите тип валюты
	public static void main(String[] args) {
		try {
			// Создается строитель документа
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Создается дерево DOM документа из файла
			Document document = documentBuilder.parse("BookCatalog.xml");

			// Получаем корневой элемент
			Node root = document.getDocumentElement();

			System.out.println("List of books:");
			System.out.println();
			// Просматриваем все подэлементы корневого - т.е. книги
			NodeList books = root.getChildNodes();
			for (int i = 0; i < books.getLength(); i++) {
				Node book = books.item(i);
				// Если нода не текст, то это книга - заходим внутрь
				if (book.getNodeType() != Node.TEXT_NODE) {
					NodeList chieldNode = book.getChildNodes();
					for (int j = 0; j < chieldNode.getLength(); j++) {
						Node bookProp = chieldNode.item(j);
						// Если нода не текст, то это один из параметров книги -
						// печатаем
						if (bookProp.getNodeType() != Node.TEXT_NODE) {
							System.out.print(
									bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
							if (bookProp.getAttributes().getLength()>0) {
								String s = bookProp.getAttributes().getNamedItem("currency").getNodeValue();
								System.out.print(s);
							}
							System.out.println();
									
						}
						
						
					}
					System.out.println("===========>>>>");
				}
			}

		} catch (ParserConfigurationException ex) {
			ex.printStackTrace(System.out);
		} catch (SAXException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}
}