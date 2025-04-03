package demo;

import java.sql.SQLException;

class FileAccessor implements AutoCloseable {

	public FileAccessor(String s) {
		// ... //...
		System.out.println("Ouverture des resources ...");
	}

	@Override
	public void close() throws Exception {

		System.out.println("Libération des ressources !!!");
	}

	void read() throws Exception {
		throw new Exception();
	}

}

class Whatever implements AutoCloseable {

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}

class Resource1 implements AutoCloseable {
	public void close() {
		System.out.println("Resource1");
	}
}

enum MyEnum {
	V1, V2
}

public class DemoClosable {


	public static void main(String[] args) {

		try (Resource1 r1 = new Resource1()) {
			System.out.println("Test");
		}

		try (FileAccessor f = new FileAccessor("/path/to/file");
				FileAccessor f2 = new FileAccessor("/path/to/file");
				FileAccessor f3 = new FileAccessor("/path/to/file");) {
			System.out.println("Utilisation de la donnée ...");
		} catch (Exception e) {

		} finally {
			System.out.println("Fin de programme");
		}

		System.out.println("------------");
		try (FileAccessor f = new FileAccessor("/path/to/file"); FileAccessor f2 = new FileAccessor("/path/to/file");) {
			f.read();
			System.out.println("Utilisation de la donnée ...");
		} catch (RuntimeException e) {
			System.out.println("Gestion de l'erreur RuntimeException");
		} catch (Exception e) {
			System.out.println("Gestion de l'erreur");
		} finally {
			System.out.println("Fin de programme");
		}
		System.out.println("------------");

		try (Whatever w = new Whatever()) {

		} catch (Exception e) {

		} finally {

		}

	}

}
