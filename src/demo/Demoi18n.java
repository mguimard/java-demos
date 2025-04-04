package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Demoi18n {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties props = new Properties();

		try (FileInputStream i = new FileInputStream(new File("/tmp/my-properties.txt"))) {
			System.out.println("Fichier chargé");
			props.load(i);

			props.keySet().forEach(System.out::println);

			System.out.println(props.getProperty("key1"));
			System.out.println(props.getProperty("key2"));
			System.out.println(props.getProperty("key3", "par défaut"));

		}
	}

	public static void decimales(String[] args) {
		NumberFormat fr = NumberFormat.getNumberInstance(new Locale("fr", "FR"));
		NumberFormat us = NumberFormat.getNumberInstance(new Locale("en", "US"));
		NumberFormat ru = NumberFormat.getNumberInstance(new Locale("ru", "RU"));

		System.out.println(fr.format(123.456));
		System.out.println(us.format(123.456));
		System.out.println(ru.format(123.456));

	}

	public static void devices(String[] args) {
		NumberFormat fr = NumberFormat.getCurrencyInstance(new Locale("fr", "FR"));
		NumberFormat us = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
		NumberFormat ru = NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));

		System.out.println(fr.format(123));
		System.out.println(us.format(123));
		System.out.println(ru.format(123));

	}

	public static void setdefalt(String[] args) {
		Locale.setDefault(new Locale("fr", "FR"));
		System.out.println(Locale.getDefault());
	}

	public static void rb(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle("demo.MyBundle", new Locale("fr", "FR"));
		rb.keySet().forEach(System.out::println);

		String myKey = rb.getString("myKey");
		System.out.println(myKey);

		System.out.println("-------");

		ResourceBundle rb2 = ResourceBundle.getBundle("demo.MyBundle", new Locale("en"));
		rb2.keySet().forEach(System.out::println);

		String myKey2 = rb2.getString("myKey");
		System.out.println(myKey2);

	}

	public static void locales(String[] args) {
		Locale locale = Locale.getDefault();
		System.out.println(locale);

		System.out.println("----");

		Arrays.asList(Locale.getAvailableLocales()).stream().filter(l -> l.getCountry().equals("IT"))
				.forEach(System.out::println);

		Arrays.asList(Locale.getAvailableLocales()).stream().filter(l -> l.getLanguage().equals("fr"))
				.map(l -> l.getDisplayCountry()).forEach(System.out::println);

		System.out.println(locale.getDisplayCountry());

	}

}
