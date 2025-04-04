package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Loc {

	public static void main(String[] args) throws IOException {
		Locale loc = new Locale("fr", "FR");
		ResourceBundle rb = ResourceBundle.getBundle("demo.MyBundle", loc);
		System.out.println(rb.getObject("test"));

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/home/morgan/tmp/somefile.txt");
		prop.load(fis);

		System.out.println(prop.getProperty("test"));

		ResourceBundle rb2 = ResourceBundle.getBundle("demo.MyBundle", Locale.getDefault());
		System.out.println(rb2.getObject("test"));

	}

	public static void main2(String[] args) {
		Locale[] loc = Locale.getAvailableLocales();
		Arrays.stream(loc).forEach(System.out::println);
		System.out.println("-----");
		Arrays.stream(loc).filter(x -> x.getLanguage().equals("fr")).forEach(System.out::println);
	}
}
