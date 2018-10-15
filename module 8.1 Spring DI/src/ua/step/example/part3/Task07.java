package ua.step.example.part3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.part3.performer.PerformanceException;
import ua.step.example.part3.performer.Performer;

/**
 *  Автоматическое связываение (autowire)
 */
public class Task07 {
	public static void main(String[] args) throws PerformanceException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring7.xml");
		Performer performer = context.getBean("kenny", Performer.class);
		performer.perform();
	}
}