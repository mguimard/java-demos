package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class DemoNIO {

	static String folderPath = "/tmp/demos/nio";
	static String fileName = "demo.txt";
	static String fileName2 = "coucou.toto";

	public static void main(String[] args) {
		Path p = Paths.get(folderPath + File.separator + fileName2);

		try (BufferedReader in = Files.newBufferedReader(p)) {

			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());

			in.close();

			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());

		} catch (IOException e) {
			System.out.println("BOOOM: " + e.getMessage());
		}

	}

	public static void mainFind(String[] args) throws IOException {

		Path dossier = Paths.get(folderPath);
		Stream<Path> stream = Files.find(dossier, 2, (p, attrs) -> {
			// Path p
			BasicFileAttributes attributes = attrs;

			return p.toFile().canRead();
		});

		long count = stream.peek(System.out::println).count();

		System.out.println(count);

	}

	public static void mainEasyRead(String[] args) throws IOException {
		Path p = Paths.get("/tmp/demos/nio/coucou.toto");
		Files.readAllLines(p).forEach(System.out::println);
	}

	public static void mainEasyWrite(String[] args) throws IOException {
		Path p = Paths.get("/tmp/demos/nio/coucou.toto");
		Files.write(p, new String("hello").getBytes());
	}

	public static void mainPath(String[] args) {
		Path p = Paths.get("/aaaa/bbbb/c/ddd/e/f/azkjehgazjuhegh");
		int count = p.getNameCount();
		System.out.println(count);
		System.out.println(p.subpath(0, count)); // ?

		Path root = Paths.get("/");
		Path p1 = Paths.get("/path/to/something");
		Path p2 = Paths.get("/path/to/something-else");

		System.out.println("p1->p1:[" + p1.relativize(p1) + "]");
		System.out.println("root->p1:[" + root.relativize(p1) + "]");
		System.out.println("p1->root:[" + p1.relativize(root) + "]");

		System.out.println("p1->p2:[" + p1.relativize(p2) + "]"); // ../
		System.out.println("p2->p1:[" + p2.relativize(p1) + "]"); // ../
	}

	public static void mainFiles(String[] args) throws IOException {
		Stream<Path> stream = Files.list(Paths.get(folderPath));
		stream.filter(p -> p.toFile().isFile()).forEach(System.out::println);
	}

	public static void mainNioBasics(String[] args) throws IOException {

		Path p = Paths.get(folderPath);
		System.out.println(p);

		File dossier = p.toFile();
		System.out.println(dossier.isFile()); // false

		File f = Paths.get(folderPath + File.separator + fileName).toFile();
		System.out.println(f);

		if (!f.exists())
			f.createNewFile();

		System.out.println(f.exists());
		System.out.println(f.canRead());
		System.out.println(f.canWrite());

		String hello = "hello";

		try {
			Files.write(f.toPath(), hello.getBytes());
		} catch (AccessDeniedException e) {
			System.out.println("Pas les droits");
		}

	}

}
