package ua.step.puzzle;

import java.util.HashMap;
import java.util.Map;

public class Upgrate
{
    public static void main(String[] args)
    {
        Map<String, String> map = new HashMap<>();
        System.out.println(map.put("abc", "111"));
        System.out.println(map.put("abc", "222"));
        System.out.println(map.put("abc", "333"));
    }
}