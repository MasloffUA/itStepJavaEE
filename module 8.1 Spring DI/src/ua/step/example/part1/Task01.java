package ua.step.example.part1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.model.HelloWorld;

/**
 * 
 * Создания бина используя java конфигурацию в Spring
 *
 */
public class Task01 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
	      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	      HelloWorld obj = context.getBean(HelloWorld.class, "helloWorld");
	      obj.getMessage();
	}
}