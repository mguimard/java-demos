package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class MyCounter implements Runnable {
	static int intcounter = 0;
	static AtomicInteger counter = new AtomicInteger(0);

	@Override
	public void run() {
		int previous = counter.getAndIncrement();

		System.out.println(previous);
		intcounter++;

	}
}

public class DemoAtomic {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(20);

		es.submit(new MyCounter());
		es.submit(new MyCounter());
		es.submit(new MyCounter());
		es.submit(new MyCounter());
		es.submit(new MyCounter());
		es.submit(new MyCounter());
		es.submit(new MyCounter());
		es.submit(new MyCounter());
		es.submit(new MyCounter());
		es.submit(new MyCounter());

		Thread.sleep(2000);

		System.out.println("Fin : " + MyCounter.intcounter);
	}

}
