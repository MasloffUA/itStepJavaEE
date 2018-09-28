package ua.step.puzzle;
import java.util.Optional;

public class Mutant
{
    public static void main(String[] args)
    {
        class CatDog implements Cat, Dog
        {
        }
        test(new CatDog());
    }

    private static void test(Object obj)
    {
        Optional.of((Cat & Dog) obj).ifPresent(x -> {
            x.gav();
            x.miau();
        });
    }

    interface Cat
    {
        default void miau()
        {
            System.out.println("мяу");
        }
    }

    interface Dog
    {
        default void gav()
        {
            System.out.println("гав");
        }
    }

}
