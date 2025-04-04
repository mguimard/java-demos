package demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Player extends Thread {
	CyclicBarrier cb;

	public Player() {
		super();
	}

	public Player(CyclicBarrier cb) {
		this.cb = cb;
		this.start();
	}

	public void run() {
		try {
			Thread.sleep((int) (Math.random() * 2000));
			System.out.println("Player finished");
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			System.out.println("ooops" + e.getMessage());
		}
	}
}

class Match implements Runnable {
	public void run() {
		System.out.println("Match is done...");
	}
}

public class TestCyclic {

	public static void main(String[] args) {
		Match match = new Match();
		CyclicBarrier cb = new CyclicBarrier(2, match);
		Player p1 = new Player(cb);
		new Player(cb);

		System.out.println("Fin de programme");
	}
}
