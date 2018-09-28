package ua.step.part1.java8.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Исполнители могут принимать список задач на выполнение с помощью метода
 * invokeAll(), который принимает коллекцию callable-задач и возвращает список
 * из Future.
 *
 */
public class Task05 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");

		executor.invokeAll(callables).stream().map(future -> {
			try {
				return future.get();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}).forEach(System.out::println);
	}
}