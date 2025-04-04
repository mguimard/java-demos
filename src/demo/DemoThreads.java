package demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class DemoThreads {

	static class MyThread implements Runnable {
		private String str;

		MyThread(String str) {
			this.str = str;
		}

		public void run() {
			Print(str.toUpperCase());
			doSomething();
		}
	}

	static class MyCallable<Truc> implements Callable<Truc> {
		private Truc t;

		MyCallable(Truc t) {
			this.t = t;
		}

		public Truc call() {
			Print(t);
			return t;
		}
	}

	public static synchronized void doSomething() {

		Print("Je commence ...");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}

		Print("J'ai fini...");

	}

	static class Printer implements Callable<String> {
		public String call() {
			System.out.println("DONE");
			return null;
		}
	}

	public static void main(String[] args) throws InterruptedException {

		Callable<String> c = new Callable<String>() {
			@Override
			public String call() throws Exception {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					Print(e.getMessage());
				}
				return "HELLO";
			}
		};

		ExecutorService es = Executors.newFixedThreadPool(10);
		List<Callable<String>> list = Arrays.asList(c, c, c, c, c);
		List<Future<String>> futures = es.invokeAll(list);
		System.out.println(futures.size());

		futures.forEach(t -> {
			try {
				String result = t.get();
				Print(result);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		es.shutdown();
	}

	public static void mainCallable(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(1);
		Future<String> result = es.submit(new MyCallable<String>("Test"));
		System.out.println("Result:" + result.get());
		es.shutdown();
	}

	public static void mainStream(String[] args) {
		IntStream.rangeClosed(1, 10).parallel().map(i -> i * 2).sequential().forEach(DemoThreads::Print);
	}

	public static void mainFixedThreadPool(String[] args) throws InterruptedException {
		// ExecutorService es = Executors.newSingleThreadExecutor();
		ExecutorService es = Executors.newFixedThreadPool(4);
		ExecutorService es2 = Executors.newFixedThreadPool(4);

		MyThread t1 = new MyThread("1");
		MyThread t2 = new MyThread("2");
		MyThread t3 = new MyThread("3");
		MyThread t4 = new MyThread("4");

		es.submit(t1);
		es.submit(t2);
		es.submit(t3);
		es.submit(t4);

		es2.submit(t1);
		es2.submit(t2);
		es2.submit(t3);
		es2.submit(t4);

		Print("Fin de programme");

		es.shutdown();
		es2.shutdown();

		es.awaitTermination(10000, TimeUnit.SECONDS);

		Print("Super fin de programme");
	}

	public static void mainExecutorService(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newSingleThreadExecutor();
		MyThread thread = new MyThread("ocp");
		Future future = es.submit(thread);
		Future future2 = es.submit(thread);
		Integer tmp = (Integer) future.get(); // Line 22
		System.out.println(tmp);
		es.shutdown();
	}

	public static void Print(String msg) {
		String tag = Thread.currentThread().getName();
		System.out.println(tag + ": " + msg);
	}

	public static void Print(int msg) {
		String tag = Thread.currentThread().getName();
		System.out.println(tag + ": " + msg);
	}

	public static void Print(Object msg) {
		String tag = Thread.currentThread().getName();
		System.out.println(tag + ": " + msg);
	}

	public static void mainThreadBasics(String[] args) throws InterruptedException {

		Thread t = new Thread() {
			@Override
			public void run() {
				Print("I'm running in a thread");
			}
		};

		t.start();

		Print(t.getState().name());

		Thread.sleep(1000);

		Print(t.getState().name());

		Print("Main thread");

		Print(t.getState().name());

	}
}
