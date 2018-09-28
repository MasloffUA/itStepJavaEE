package ua.step.part2.anotation;

@Version3(42)
//@Version3(value = 42, author = "Jim Smith")
//@Version3(42, author = "Jim Smith") // так нельзя
public class Task03 {

}

@interface Version3 {
    int value();
    String author() default "UNKNOWN";
}