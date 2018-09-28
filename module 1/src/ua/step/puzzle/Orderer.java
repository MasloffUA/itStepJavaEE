package ua.step.puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Orderer {

	public static void main(String[] args) {
		Integer[] mas = new Integer[100000];
		Arrays.setAll(mas, (x) -> x + 1);
		List<Integer> list = Arrays.stream(mas).parallel().unordered().
				filter(x -> (x & 0xFF) == 0).
				collect(Collectors.toList());
		list.forEach(System.out::println);

	}
}