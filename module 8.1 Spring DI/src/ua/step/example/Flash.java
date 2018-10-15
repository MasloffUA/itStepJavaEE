package ua.step.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.di.constructor.flashlight.Flashlight;


public class Flash {
	public static void main(String[] args) {	
	      @SuppressWarnings("resource")
	      ApplicationContext context = new ClassPathXmlApplicationContext("BeansFlash.xml");
	      Flashlight flashlight = context.getBean(Flashlight.class, "createFlash");
	      flashlight.swithOn();
	      System.out.println(flashlight.isShines());
	}
}
