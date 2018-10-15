package ua.step.example.part3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.part3.performer.PerformanceException;
import ua.step.example.part3.performer.Performer;

/**
 *  Автоматическое связываение
 *  @see ua.step.example.part3.performer.Automatic
 *  @see ua.step.example.part3.performer.Drum
 */
public class Task08 {
	public static void main(String[] args) throws PerformanceException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring8.xml");
		Performer performer = context.getBean("automatic", Performer.class);
		performer.perform();
	}
}