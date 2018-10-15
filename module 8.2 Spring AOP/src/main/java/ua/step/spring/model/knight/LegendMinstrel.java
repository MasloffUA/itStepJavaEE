package ua.step.spring.model.knight;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LegendMinstrel {
	@Before("@annotation(ua.step.spring.model.knight.Legend)")
	public void myAdvice(){
		System.out.println("Это легендарное путешествие!!!");
	}
}
