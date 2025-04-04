package demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Course {

	class EndTask implements Runnable {
		@Override
		public void run() {
			voitures.forEach(Thread::interrupt);
			System.out.println("On peut enfin donner les médailles");
		}
	}

	class VoitureDeCourse extends Thread {

		String name;

		public VoitureDeCourse(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println(name + ": GOOOOO");

			try {
				Thread.sleep(1000 + (int) (Math.random() * 5000));
				int myPosition = ligneArrivee.await();
				System.out.println(name + ": Je suis passé " + System.nanoTime());
				System.out.println(name + ": Je suis en position " + (myPosition + 1));

			} catch (InterruptedException | BrokenBarrierException e) {
				System.out.println(name + ": c'est pas juste...");
			}

			System.out.println(name + ": Je rentre au garage");
		}

	}

	CyclicBarrier ligneArrivee = new CyclicBarrier(2, new EndTask());

	List<VoitureDeCourse> voitures = Arrays.asList(new VoitureDeCourse("A"), new VoitureDeCourse("B"),
			new VoitureDeCourse("C"));

	void topDepart() {
		voitures.forEach(VoitureDeCourse::start);
	}
}

public class DemoCyclic {

	public static void main(String[] args) {
		Course c = new Course();
		c.topDepart();
	}

}
