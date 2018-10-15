package ua.step.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.model.HelloWorld;

/**
 * 
 * События
 *
 */
public class Task07 {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans2.xml");

		// Событие старта
		context.start();

		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();

		// событие окончания
		context.stop();
	}
}
