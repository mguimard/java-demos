package demo;

public class MyApp {

	static class MyThread extends Thread {
		@Override
		public void run() {
			print("Je suis un thread");
			doSomething();
		}
	}

	public static synchronized void doSomething() {
		print("Je commence....");

		// ....
		try {
			Thread.sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {
		}

		print("J'ai fini");
	}

	public static void print(Object msg) {
		System.out.println(Thread.currentThread().getName() + ": " + msg);
	}

	public static void main(String[] args) throws InterruptedException {
		print("Début de programme");

		Thread t1 = new MyThread();
		Thread t2 = new MyThread();

		t1.start();
		t2.start();

		print("Fin de programme");
	}

	public static void mainWhatIsAThread(String[] args) {
		Thread main = Thread.currentThread();
		System.out.println(main.getName());
		System.out.println(main.getId());
		System.out.println(main.isAlive());
	}

}
