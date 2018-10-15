package ua.step.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.scene.image.Image;
import ua.step.example.mailer.ImapMailSender;
import ua.step.example.mailer.Mailer;

/**
 * 
 * Внедрение через конструктор
 *
 */
public class Task05 {
	public static void main(String[] args) {
	      ApplicationContext context = new ClassPathXmlApplicationContext("mailer_bean.xml");
	      
	      Mailer mailer = context.getBean(Mailer.class, "mailer");
	      mailer.send("Aloha");
	}
}
