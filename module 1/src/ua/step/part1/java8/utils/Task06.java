package ua.step.part1.java8.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 
 * CompletableFuture - средство для передачи информации между параллельными
 * потоками исполнения. По существу это блокирующая очередь, способная передать
 * только одно ссылочное значение. В отличие от обычной очереди, передает также
 * исключение, если оно возникло при вычислении передаваемого значения.
 *
 */
public class Task06 {
	public static void main(String... args) throws ExecutionException, InterruptedException {
		final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> doSomethingAndReturnA())
				.thenApply(a -> convertToB(a));

		future.get();
	}

	private static int convertToB(final String a) {
		System.out.println("convertToB: " + Thread.currentThread().getName());
		return Integer.parseInt(a);
	}

	private static String doSomethingAndReturnA() {
		System.out.println("doSomethingAndReturnA: " + Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "1";
	}
}