package ua.step.part2.anotation;

import java.lang.annotation.Repeatable;

public class Task08 {

}

@Repeatable(Annotations.class)
@interface Annotation {
	String value();
}

@interface Annotations {
	Annotation[] value();
}

// Использование множества аннотаций одного типа. Добавлено в java 8
@Annotation("1")
@Annotation("2")
class Book {
}