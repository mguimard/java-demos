package demo;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class DemoThreadAndStreams {

	public static void main__(String[] args) {

		OptionalInt o = IntStream.range(0, 100).parallel().findAny();
		int i = o.getAsInt();

		IntStream.range(0, 100).parallel().forEachOrdered(System.out::println);

	}

	public static void Print(Object msg) {
		String tag = Thread.currentThread().getName();
		System.out.println(tag + ": " + msg);
	}

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("A", "B", "C", "D");
		// IntStream.range(0, 100).parallel().forEach(DemoThreadAndStreams::Print);
		// strings.stream().parallel().forEach(DemoThreadAndStreams::Print);

		// V1 10 threads possible
		// minimum 10s

		long startV1 = System.currentTimeMillis();
		IntStream.range(0, 100).parallel().forEachOrdered(DemoThreadAndStreams::Process);
		long endV1 = System.currentTimeMillis();

		System.out.println(endV1 - startV1);

		long startV2 = System.currentTimeMillis();
		// V2 100 * 100 s
		for (int i = 0; i < 100; i++) {
			Process(i);
		}
		long endV2 = System.currentTimeMillis();

		System.out.println(endV2 - startV2);

	}

	public static void Process(Object i) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}

		// System.out.println(i);
	}

}
