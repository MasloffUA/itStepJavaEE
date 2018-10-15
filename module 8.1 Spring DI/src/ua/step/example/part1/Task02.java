package ua.step.example.part1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.step.example.model.HelloWorld;
import ua.step.example.model.HelloWorldConfig;

/**
 * 
 * Конфигурация контекста с помошью аннотаций. Контекст бина
 *
 */
public class Task02 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		helloWorld.setMessage("Hello World!");
		helloWorld.getMessage();
		
		
		helloWorld = ctx.getBean(HelloWorld.class);
		helloWorld.getMessage();
	}
}