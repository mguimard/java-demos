package demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DemoRunnable {

	static class MyRunnable implements Runnable {

		String name;

		public MyRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			print("Runnable " + name + " running....");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			print("Runnable " + name + " finished....");

		}
	}

	public static void print(Object msg) {
		System.out.println(Thread.currentThread().getName() + ": " + msg);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		print("Début de programme");

		ExecutorService s1 = Executors.newSingleThreadExecutor();
		ExecutorService s2 = Executors.newFixedThreadPool(4);

		MyRunnable r1 = new MyRunnable("A");
		MyRunnable r2 = new MyRunnable("B");

		Future<?> f1 = s2.submit(r1);
		Future<?> f2 = s2.submit(r2);

		System.out.println(f1.get());

		print("Fin de programme");

	}

}
