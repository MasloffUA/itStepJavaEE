package ua.step.puzzle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Indestructible
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>(Arrays.asList("Arne", "Chuck", "Slay"));
        
        list.stream().forEach(x -> {
            if (x.equals("Chuck"))
            {
                list.remove(x);
            }

        });
        
        list.removeIf("Chuck"::equals);
    }
}