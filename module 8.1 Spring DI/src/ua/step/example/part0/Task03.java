package ua.step.example.part0;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.part0.mailer.Mailer;

/**
 * 
 * Внедрение объекта через конструктор
 *
 */
public class Task03 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		  // задание контекста спринг приложения
	      ApplicationContext context = new ClassPathXmlApplicationContext("mailer_bean.xml");
	      
	      // получение спринг бина по его имени 
	      Mailer mailer = context.getBean(Mailer.class, "mailer");
	      
	      // использование бина
	      mailer.send("Aloha");
	}
}