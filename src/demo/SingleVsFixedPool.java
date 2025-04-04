package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyRunnable2 implements Runnable {

	private String name;

	MyRunnable2(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name);
	}
}

public class SingleVsFixedPool {

	public static void main(String[] args) {
		/*
		 * ExecutorService es = Executors.newSingleThreadExecutor();
		 * 
		 * es.submit(new MyRunnable2("A")); es.submit(new MyRunnable2("B"));
		 * es.submit(new MyRunnable2("C")); es.submit(new MyRunnable2("D"));
		 * es.submit(new MyRunnable2("E"));
		 */

		System.out.println("------");

		ExecutorService es2 = Executors.newFixedThreadPool(3);

		es2.submit(new MyRunnable2("A"));
		es2.submit(new MyRunnable2("B"));
		es2.submit(new MyRunnable2("C"));
		es2.submit(new MyRunnable2("D"));
		es2.submit(new MyRunnable2("E"));

	}

}
