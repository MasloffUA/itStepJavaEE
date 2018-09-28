package ua.step.puzzle;

import java.math.BigInteger;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

public class Fibonatchi {

	static final int SIZE = 4096;
	
	BinaryOperator<BigInteger> action;
	
	@Param({"1", "1024"})
	int backoff;
	
	@Setup
	public void setup() {
		int back = backoff;
		action = (x,y) -> {
			// загрузка процессора
			Blackhole.consumeCPU(back);
			return x.add(y);
		};
	}
	
	@Benchmark
	public void classicF() {
		BigInteger first = BigInteger.ONE;
		BigInteger second = BigInteger.ONE;
		BigInteger sum = BigInteger.ZERO;
		for (int i = 0; i < SIZE; i++) {
			BigInteger temp = second.add(first);
			first = second;
			second = temp;
			sum = action.apply(sum, first);
		}
	}

	@Benchmark
	public void streamF() {
		stream().limit(SIZE).reduce(BigInteger.ZERO, action);
	}

	private Stream<BigInteger> stream() {
		return Stream.generate(new Supplier<BigInteger>() {
			BigInteger first = BigInteger.ONE;
			BigInteger second = BigInteger.ONE;
			@Override
			public BigInteger get() {
				BigInteger temp = second.add(first);
				first = second;
				second = temp;
				return null;
			}
		});
	}
}