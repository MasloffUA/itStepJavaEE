package ua.step.part1.java8.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * 
 * Чтение строковой информации из файла.
 *
 */
public class Task02 {
	public static void main(String[] args) throws IOException {
		File file = new File("test.txt");
		Stream<String> strings = Files.lines(file.toPath());
		strings.forEach(System.out::println);
		strings.close();
	}
}
