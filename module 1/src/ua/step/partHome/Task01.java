package ua.step.partHome;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.AttributedCharacterIterator.Attribute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * Назначает валюту по умолчанию в случаях:
 * 1. Если у элемента cost отсутствуют атрибуты
 * 2. Если у элемента cost отсутствует атрибут currency
 * 3. Если currency null
 * 4. Если currency isEmpty
 *
 * После выполнения программы удаляет созданную книгу.
 * 
 */
public class Task01 {
	// Использую для удаления книги после выполнения
	private static Element addedBook;
	
	public static void main(String[] args) {

		try {
			// Создается строитель документа
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Создается дерево DOM документа из файла
			Document document = documentBuilder.parse("BookCatalog.xml");
			
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \nСписок книг ДО добавления новой книги.");
			printBooks(document);
			
			addBook(document);
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \nСписок книг ПОСЛЕ добавления новой книги.");
			printBooks(document);
			
			checkPrice(document);
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \nСписок книг ПОСЛЕ добавления валюты по умолчанию в третью книгу");
			printBooks(document);
			
			// Удаление добавленной книги (:
			removeBook(document);
			


		} catch (ParserConfigurationException ex) {
			ex.printStackTrace(System.out);
		} catch (SAXException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static void addBook(Document document) {
		// Получаем корневой элемент
		Node root = document.getDocumentElement();

		// Создаем новую книгу по элементам
		// Сама книга <Book>
		Element book = document.createElement("Book");
		// <Title>
		Element title = document.createElement("Title");
		// Устанавливаем значение текста внутри тега
		title.setTextContent("Incredible book about Java");
		// <Author>
		Element author = document.createElement("Author");
		author.setTextContent("Saburov Anton");
		// <Date>
		Element date = document.createElement("Date");
		date.setTextContent("2015");
		// <ISBN>
		Element isbn = document.createElement("ISBN");
		isbn.setTextContent("0-06-999999-9");
		// <Publisher>
		Element publisher = document.createElement("Publisher");
		publisher.setTextContent("Java-Course publisher");
		// <Cost>
		Element cost = document.createElement("Cost");
		cost.setTextContent("499");
		// Устанавливаем атрибут
		cost.setAttribute("currency", null);

		// Добавляем внутренние элементы книги в элемент <Book>
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(date);
		book.appendChild(isbn);
		book.appendChild(publisher);
		book.appendChild(cost);
		// Добавляем книгу в корневой элемент
		root.appendChild(book);
		
		addedBook = book;

		// Записываем XML в файл
		writeDocument(document);
	}

	public static void checkPrice(Document document) {
		Node root = document.getDocumentElement();
		NodeList books = root.getChildNodes();
		for (int i=0; i<books.getLength(); i++) {
			Node book = books.item(i);
			if (book.getNodeType() != Node.TEXT_NODE) {
				NodeList childNode = book.getChildNodes();
				for (int j=0; j<childNode.getLength(); j++){
					Node bookProp = childNode.item(j);
					if (bookProp.getNodeType() != Node.TEXT_NODE) {
						if (bookProp.getNodeName().equalsIgnoreCase("cost")) {
							if (!bookProp.hasAttributes()
									|| bookProp.getAttributes().getNamedItem("currency") == null
									|| bookProp.getAttributes().getNamedItem("currency").getNodeValue() == null
									|| bookProp.getAttributes().getNamedItem("currency").getNodeValue().isEmpty()) {

								Element cost = (Element) bookProp;
								cost.setAttribute("currency", "UAH");
								writeDocument(document);
							}
						}

					}

				}
			}
		}
	}
			
			
		
	// Функция для сохранения DOM в файл
	private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(document);
			FileOutputStream fos = new FileOutputStream("BookCatalog.xml");
			StreamResult result = new StreamResult(fos);
			tr.transform(source, result);
		} catch (TransformerException | IOException e) {
			e.printStackTrace(System.out);
		}
	}

	public static void printBooks(Document document) {
		// Получаем корневой элемент
		Node root = document.getDocumentElement();

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
	}
	
	public static void removeBook(Document document) {
		Node root = document.getDocumentElement();
		root.removeChild(addedBook);
		writeDocument(document);
	}
	
	
}