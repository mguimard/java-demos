package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class PainAuChocolat {
	@Override
	public String toString() {
		return "miam miam";
	}
}

class FournisseurDePainChoc implements Callable<PainAuChocolat> {

	String name;

	FournisseurDePainChoc(String name) {
		this.name = name;
	}

	@Override
	public PainAuChocolat call() throws Exception {
		System.out.println(name + ": Je vais chercher un pain choc");

		Thread.sleep((int) (Math.random() * 5000));

		System.out.println(name + ": return");

		if (Math.random() > 0.5)
			return null;

		return new PainAuChocolat();
	}
}

public class DemoCallable {

	public static void main2(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);

		es.submit(() -> 1234);

		es.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				return 1234;
			};
		});

		es.submit(() -> {
			System.out.println("coucou");
		});

		es.submit(new Runnable() {
			public void run() {
				System.out.println("coucou");
			};
		});

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(10);

		FournisseurDePainChoc fournisseur1 = new FournisseurDePainChoc("A");
		FournisseurDePainChoc fournisseur2 = new FournisseurDePainChoc("B");

		List<FournisseurDePainChoc> fournisseurs = Arrays.asList(fournisseur1, fournisseur2);

		List<Future<PainAuChocolat>> promesses = es.invokeAll(fournisseurs);

		List<PainAuChocolat> results = new ArrayList<>();

		for (Future<PainAuChocolat> promesse : promesses) {
			PainAuChocolat p = promesse.get();
			System.out.println(p);

			if (p != null) {
				results.add(p);
			}
		}

		mange(results);

		// promesses.forEach(promesse -> {
		// try {
		// PainAuChocolat p = promesse.get();
		// System.out.println(p);
		// } catch (InterruptedException | ExecutionException e) {
		// e.printStackTrace();
		// }
		// });

//		Future<PainAuChocolat> promesse = es.submit(fournisseur1);
		// PainAuChocolat p = promesse.get();

		// System.out.println(p); // ?
	}

	private static void mange(List<PainAuChocolat> results) {
		System.out.println("Je vais pouvoir manger " + results.size() + " pain(s) choc");

	}

}
