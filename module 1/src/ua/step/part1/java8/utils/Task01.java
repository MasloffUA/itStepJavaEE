package ua.step.part1.java8.utils;

/**
 * 
 * Объедененеие строк с разделителем.
 *
 */
public class Task01 {
	public static void main(String[] args) {
		String string = String.join("-", "Hello","my", "world");
		System.out.println(string);
	}
}
