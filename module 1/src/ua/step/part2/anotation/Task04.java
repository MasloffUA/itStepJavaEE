package ua.step.part2.anotation;

@Author({ "Jim", "Bob", "Sara" })
public class Task04 {

}

@interface Author {
	String[] value() default {};
}
/**
 * Атрибуты могут иметь только следующие типы примитивы String, Class или «any
 * parameterized invocation of Class», enum, annotation, массив элементов любого из
 * вышеперечисленных типов
 */