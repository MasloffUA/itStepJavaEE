package ua.step.part2.anotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import ua.step.part2.anotation.model.MyClassAnnotation;
import ua.step.part2.anotation.model.MyMethotAnnotation;

@Author
@Version3(2)
@MyClassAnnotation()
public class Task05 {
	public static void main(String[] args) {
		// Исследование кода и нахождение анотированных методов и классов
		Task05 task = new Task05();
		Annotation[] classAnotations = task.getClass().getAnnotations();
		printAnnotation(classAnotations);
		System.out.println();
		Method[] classMethods = task.getClass().getMethods();
		for (Method method : classMethods) {
			Annotation[] methodAnnotations = method.getAnnotations();
			printAnnotation(methodAnnotations);
			if (methodAnnotations.length > 0) {
				System.out.println(method);
			}
		}
	}

	private static void printAnnotation(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
		}

	}

	@MyMethotAnnotation(length = 10)
	public void name() {

	}

	@MyMethotAnnotation()
	public void value() {

	}
	public void none() {

	}
}
