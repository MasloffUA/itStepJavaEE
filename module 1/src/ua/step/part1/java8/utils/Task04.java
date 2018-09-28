package ua.step.part1.java8.utils;

import java.io.IOException;
import java.util.Random;

/**
 * 
 * Новая функциональность в классе Random
 *
 */
public class Task04 {
	public static void main(String[] args) throws IOException {
		// Выведет 10 случайных числел от 20 (включительно) до 100 (исключительно) 
		new Random().ints(10, 20, 100).forEach(System.out::println);
	}
}