package ua.step.puzzle;
import java.util.stream.Stream;

public class MadMax
{
    public static void main(String[] args)
    {
        System.out.println(Stream.of(-3, -2, -1, 0, 1, 2, 3).max(Math::max).get());
    }
}