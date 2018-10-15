package ua.step.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.step.example.model.HelloWorld;
import ua.step.example.model.HelloWorldConfig;

/**
 * 
 * Конфигурация с помошью аннотаций
 *
 */
public class Task06 {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		helloWorld.setMessage("Hello World!");
		helloWorld.getMessage();
		
		
		helloWorld = ctx.getBean(HelloWorld.class);
		helloWorld.getMessage();
	}
}
