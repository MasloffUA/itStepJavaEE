package ua.step.part2.anotation.model;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMethotAnnotation {
	int length() default 30;
}
