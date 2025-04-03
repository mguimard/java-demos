package demo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

class Beer implements Serializable {
	String model = "IPA";
}

public class DemoIo {

	static String folderPath = "/tmp/demos";
	static String fileName = folderPath + "/" + "fichier.txt";
	static String beerFileName = folderPath + "/" + "beers.txt";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (DataOutputStream o = new DataOutputStream(new FileOutputStream(fileName))) {
			o.writeFloat(3.14f);
			o.writeInt(1);
		}

		try (DataInputStream i = new DataInputStream(new FileInputStream(fileName))) {
			float f = i.readFloat();
			System.out.println(f);

			byte b = i.readByte();
			System.out.println(b);

			b = i.readByte();
			System.out.println(b);

			b = i.readByte();
			System.out.println(b);

			b = i.readByte();
			System.out.println(b);
		}

	}

	public static void mainDataInput(String[] args) throws FileNotFoundException, IOException {
		try (DataInputStream i = new DataInputStream(new FileInputStream(fileName))) {
			int bytes = i.available();

			while (i.available() > 0) {
				int octet = i.read();
				System.out.println(octet);
			}

			i.read();
			i.read();
			i.read();
			i.read();
			i.read();

			int nextByte = i.read();
			System.out.println(nextByte);

			i.close();
			i.read();
		}
	}

	public static void mainWriterReader(String[] args) throws FileNotFoundException {
		try (PrintWriter p = new PrintWriter(fileName)) {
			p.write("Hey !");
		}

		try (FileReader f = new FileReader(fileName); BufferedReader buf = new BufferedReader(f)) {

			String line = buf.readLine();
			System.out.println(line);
		} catch (IOException e) {

		}

	}

	public static void mainOUTandIN(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Beer b = new Beer();

		try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(beerFileName))) {
			o.writeObject(b);
		}

		try (ObjectInputStream i = new ObjectInputStream(new FileInputStream(beerFileName))) {
			Beer theBeer = (Beer) i.readObject();
			System.out.println(theBeer.model);
		}
	}

	public static void mainIN2(String[] args) {
		try (InputStream i = new FileInputStream(fileName)) {

			int bytes = i.available();
			System.out.println(bytes);

			int read = i.read();
			System.out.println(read); // ?

			// i.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Fin de programme");
	}

	public static void mainOUT2(String[] args) {
		try (OutputStream o = new FileOutputStream(fileName)) {

			String s = "hello world";

			// char c = 'B';
			o.write(s.getBytes());

			// o.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Fin de programme");
	}

	public static void mainIN(String[] args) {
		try (InputStream i = new FileInputStream("/path/to/file")) {

			i.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
