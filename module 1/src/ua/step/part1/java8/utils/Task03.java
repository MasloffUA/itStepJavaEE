package ua.step.part1.java8.utils;

import java.io.IOException;
import java.util.PrimitiveIterator.OfInt;
import java.util.stream.IntStream;

/**
 * 
 * Итератор по примитивным типам
 *
 */
public class Task03 {
	public static void main(String[] args) throws IOException {
		OfInt iterator = IntStream.rangeClosed(3, 11).iterator();
		while (iterator.hasNext()) {
		    System.out.printf("%d ", iterator.nextInt()); 
		}
	}
}
