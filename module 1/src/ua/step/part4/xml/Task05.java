package ua.step.part4.xml;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

/**
 * StAX - последовательное формирование XML документа 
 *
 */
public class Task05 {
	public static void main(String[] args) {
		XMLOutputFactory factory      = XMLOutputFactory.newInstance();
		 try {
		     XMLStreamWriter writer = factory.createXMLStreamWriter(
		             new FileWriter("output2.xml"));
		     
		     // выравнивание
		     //writer = new IndentingXMLStreamWriter(writer);
		     
		     writer.writeStartDocument();
		     writer.writeStartElement("country");
		     writer.writeAttribute("name", "Ukraina");
		     writer.writeStartElement("city");
		     writer.writeAttribute("name", "Odesa");
		     writer.writeCharacters("The Best");
		     writer.writeEntityRef("amp");
		     writer.writeCData("js");
		     writer.writeEmptyElement("street");
		     writer.writeAttribute("name", "Sadovaya");
		     writer.writeEndElement();
		     writer.writeEndElement();
		     writer.writeEndDocument();

		     writer.flush(); // сохранение в файл
		     writer.close();
		     System.out.println("Saved output.xml");

		 } catch (XMLStreamException e) {
		     e.printStackTrace();
		 } catch (IOException e) {
		     e.printStackTrace();
		 }
	}
}