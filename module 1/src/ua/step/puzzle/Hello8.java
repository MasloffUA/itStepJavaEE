package ua.step.puzzle;
import java.util.function.Supplier;

public class Hello8
{
    static String str = "привет";
    public static void main(String[] args)
    {
    	
        Supplier<String> s1 = str::toUpperCase;
        Supplier<String> s2 = () -> str.toUpperCase();

        
        str = "hello";
        
        
        // В первом случае 
        System.out.println(s1.get());
        
        
        System.out.println(s2.get());
    }
}
