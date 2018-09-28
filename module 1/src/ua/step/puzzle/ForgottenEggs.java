// Решено

package ua.step.puzzle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * Можно ли добавлять элементы в коллекцию через stream?
 *
 */
public class ForgottenEggs
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();
        list.add("молоко");
        list.add("хлеб");
        list.add("колбаса");
        Stream<String> stream = list.stream();
        list.add("яйца, яйца еще");
        //stream.forEach(System.out::println);
        
        /** В таком виде сработало */
        Stream.of("", "икра", "крабы", "валюта", "золото", "суд", "Сибирь").forEach(x -> list.add(x));
        stream = list.stream();
        stream.forEach(System.out::println);
        
    }
}