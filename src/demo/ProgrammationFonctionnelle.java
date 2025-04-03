package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@FunctionalInterface
interface ExampleConsumer<T> {
	void accept(T t);
}

@FunctionalInterface
interface ExampleSupplier<T> {
	T get();
}

@FunctionalInterface
interface ExamplePredicate<T> {
	boolean test(T t);
}

@FunctionalInterface
interface CuitQQChoseEtLivreLe<T> {
	T doIt(String livreur, String cuistot);
}

@FunctionalInterface
interface ExampleFunction<Entree, Sortie> {
	Sortie apply(Entree e);
}

@FunctionalInterface
interface ExampleUnaryOperator<Truc> {
	/* public abstract */ Truc apply(Truc e);
}

@FunctionalInterface
interface ExampleUnaryOperator2<Truc> extends ExampleFunction<Truc, Truc> {
}

class Plat {
}

class Cuistot {
	public static void cuisine(String plat) {
		System.out.println("je cuisine " + plat);
	}
}

public class ProgrammationFonctionnelle {

	public static void main(String[] args) {
		Optional<String> o = Optional.empty();

		if (o.isPresent()) {
			System.out.println("Valeur présente");
		} else {
			System.out.println("Valeur non présente");
		}

		String s = o.orElse("String pas présente");
		System.out.println(s); // ?

		String result = o.orElseThrow(() -> new RuntimeException());

	}

	public static void demoStreams() {

		Predicate<String> isNull = s -> s == null;

		Supplier<String> cnews = () -> {
			if (Math.random() > 0.1)
				return "Super nouvelle wow";
			return null;
		};

		boolean hasNullData = Stream.generate(cnews).peek(System.out::println)
				.anyMatch(isNull);

		final Integer i[] = {0};
		Stream.generate(() -> i[0]++).peek(System.out::println)
				.anyMatch(e -> e == 10);

		Stream.generate(() -> "A").limit(10).forEach(System.out::println);

		System.out.println(hasNullData);

		List<String> myList = new ArrayList<String>();
		myList.stream();

		Map<String, String> map = new HashMap<>();
		Stream<String> stream = Stream.of("A", "B", "C");

		long count = stream.peek(System.out::println)
				.filter(s -> s.startsWith("A")).peek(System.out::println)
				.count();

		stream.forEach(s -> { // boom
			System.out.println(s); // ?
		});

		System.out.println(count);
		System.out.println("Fin de programme");
	}

	public static void demoFunctions() {

		Arrays.asList("pizza", "pates").stream()
				.forEach(s -> Cuistot.cuisine(s));
		Arrays.asList("pizza", "pates").stream().forEach(Cuistot::cuisine);
		Arrays.asList("pizza", "pates").stream().forEach(System.out::println);

		Function<String, String> traductionAnglais = s -> {
			System.out.println("traductionAnglais: " + s);
			return s.replace("bonjour", "hello");
		};

		BinaryOperator<String> concatAndUppercase = (s1, s2) -> {
			System.out.println("concatAndUppercase: " + s1 + " " + s2);
			return s1.concat(" ").concat(s2).toUpperCase();
		};

		UnaryOperator<String> lowerCase = s -> {
			System.out.println("lowerCase " + s);
			return s.toLowerCase();
		};

		BiFunction<String, String, String> both = concatAndUppercase
				.andThen(lowerCase);

		Function<String, String> splitCsv = s -> {
			System.out.println("splitCsv: " + s);
			String t[] = s.split(";");
			return both.apply(t[0], t[1]);
		};

		Function<String, String> splitThenTranslate = traductionAnglais
				.compose(splitCsv);

		String result = splitThenTranslate.apply("bonjour;world");

		System.out.println(result);
	}

	public static void demoFunction() {
		Function<String, Plat> cuisine1 = (s) -> {
			if (s.equals("pizza")) {
				return new Plat();
			}
			return null;
		};

		Plat pates = cuisine1.apply("pates");
		Plat pizza = cuisine1.apply("pizza");

		System.err.println(pates); // null
		System.err.println(pizza); // demo.Plat@4eec7777

	}

	public static void demoBasicSuppliersConsumers() {

		Supplier<String> journal = () -> "Super nouvelle";
		Supplier<String> cnews = () -> "Super nouvelle wow";
		Supplier<String> bfm = () -> "Super nouvelle coucou";

		String str = journal.get();
		System.out.println(str);

		ExampleSupplier<String> journal2 = () -> "Super nouvelle 2";
		String str2 = journal2.get();
		System.out.println(str2);

		String s1 = getData(journal);
		String s2 = getData(cnews);
		String s3 = getData(bfm);

		Consumer<String> lecteur = (data) -> {
			System.out.println("Je lis le journal :" + data);
		};

		lecteur.accept(bfm.get());

		Predicate<String> nouvelleTropLongue = (data) -> data.length() > 5;

		boolean estTropLongue = nouvelleTropLongue.test(bfm.get());
		System.out.println("Est trop longue: " + estTropLongue);
		System.out.println(nouvelleTropLongue.test("ABC"));

		List<String> strings = new ArrayList<>(
				Arrays.asList("A", "ABC", "ABCDEF"));
		strings.removeIf(nouvelleTropLongue);
		strings.forEach(lecteur);

		CuitQQChoseEtLivreLe<Plat> chaine = (l, c) -> new Plat();
		chaine.doIt("robert", "gégé");
	}

	public static <Truc> Truc getData(Supplier<Truc> supplier) {
		return supplier.get();
	}
}
