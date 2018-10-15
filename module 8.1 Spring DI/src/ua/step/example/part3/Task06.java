package ua.step.example.part3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.part3.performer.PerformanceException;
import ua.step.example.part3.performer.Performer;

/**
 *  Spring позволяет вам делать настоящую магию, вызывая один метод вместо другого 
 *
 */
public class Task06 {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring6.xml");
		Performer performer = context.getBean("harry", Performer.class);
		performer.perform();
		// FIXME раскоментируй строку в spring6.xml чтобы увидеть настояшую магию 
	}
}