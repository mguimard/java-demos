package demo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

class MyAnimal implements Serializable {
	String name = "Felix";
}

public class DemoIo2 {
	static String FOLDER = "/tmp";
	static String PATH = "/tmp/myfile.txt";

	public static void main(String[] args) {

	}

	public static void main12(String[] args) throws IOException {
		Files.readAllLines(Paths.get(PATH)).stream().forEach(System.out::println);
	}

	public static void main11(String[] args) throws IOException {
		Path path = Paths.get(PATH);
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String str = null;
			while ((str = reader.readLine()) != null) {
				System.out.println(str);
			}
		}

	}

	public static void main10(String[] args) throws IOException {

		Path p = Paths.get(FOLDER);
		System.out.println(p);
		System.out.println(p.getParent());
		System.out.println(p.toFile());
		System.out.println(p.toFile().canRead());
		System.out.println(p.toFile().canWrite());
		System.out.println(p.subpath(0, 1));
		System.out.println("---");

		Files.find(p, 1, (path, attrs) -> path.toFile().isFile()).forEach(System.out::println);

	}

	public static void main9(String[] args) {
		try (PrintWriter bw = new PrintWriter(PATH)) {
			bw.write(49);
			bw.close();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public static void main8(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter any number between 1 and 10: ");
		int num = br.read();
		System.out.println(num);
	}

	public static void main0(String[] args) {
		File file = new File(PATH);
		try (DataOutputStream os = new DataOutputStream(new FileOutputStream(file));
				DataInputStream is = new DataInputStream((new FileInputStream(file)))) {
			os.writeChars("JAVA");
			// System.out.println(is.readChar());
			System.out.println(is.readUTF());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main7(String[] args) {
		try (FileInputStream in = new FileInputStream(PATH)) {

			int available = in.available();
			System.out.println(available);
			byte b[] = new byte[in.available()];

			for (int i = 0; i < available; i++)
				System.out.println(b[i]);

			int readvytes = in.read(b, 0, available);
			// int readvytes = in.read(b);
			System.out.println(readvytes);
			String s = new String(b, StandardCharsets.ISO_8859_1);
			System.out.println(s);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main4(String[] args) {
		File dir = new File(FOLDER);
		Arrays.asList(dir.listFiles()).stream().filter(File::isFile).forEach(System.out::println);
	}

	public static void main3(String[] args) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
			oos.writeObject("hello world");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main2(String[] args) {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((PATH)));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
			oos.writeObject(new MyAnimal());
			MyAnimal felix = (MyAnimal) ois.readObject();
			System.out.println(felix);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
