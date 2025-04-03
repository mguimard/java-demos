package demo;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Kebab {
	boolean bon;
	public Kebab(boolean bon) {
		this.bon = bon;
	}

	@Override
	public String toString() {
		return "Kebab: " + bon;
	}
}

class Kebabier {
	public static Stream<Kebab> GoStream(int numberOfKebabs) {
		Supplier<Kebab> fluxDeKebabs = () -> new Kebab(Math.random() > 0.5);
		return Stream.generate(fluxDeKebabs).limit(numberOfKebabs);
	}
}

class TesteurDeKebab {
	boolean gouteEtTeste(Kebab k) {
		return k.bon;
	};
}

class BoiteALivrer<Truc> {
	Truc t;
	BoiteALivrer(Truc t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "Boite contenant: " + t.toString();
	}
}

class LivreurUberEats<Truc> {
	Optional<BoiteALivrer<Truc>> prepare(Truc t) {
		System.out.println("Préparation ...");

		if (Math.random() > 0.5) {
			return Optional.empty();
		}

		return Optional.of(new BoiteALivrer<Truc>(t));
	}
}

public class KebabDemo {
	public static void main(String[] args) {

		TesteurDeKebab jeanKevin = new TesteurDeKebab();
		LivreurUberEats<Kebab> leLivreurDeKebabs = new LivreurUberEats<Kebab>();

		Kebabier.GoStream(10).filter(jeanKevin::gouteEtTeste)
				.map(leLivreurDeKebabs::prepare).forEach(System.out::println);

	}
}
