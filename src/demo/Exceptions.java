package demo;

import java.io.FileNotFoundException;

class FileException extends Exception {
}

class FileTooBigException extends FileException {
}

class NotAnImageException extends FileException {
}

abstract class FileReader implements CanRead {
	public abstract void read() throws FileException;
}

class ImageFileReader extends FileReader {
	@Override
	public void read() throws NotAnImageException {

	}
}

interface CanRead {
	abstract void read() throws FileException;
}

class A {
	void f() throws NullPointerException {

	};
}

class B extends A {
	void f() throws RuntimeException {

	};

	void x() throws Exception, Throwable, Error, FileNotFoundException {

	}
}

public class Exceptions {

	public static void main(String[] args) {

		FileReader reader = new ImageFileReader();

		try {
			reader.read();
		} catch (FileException e) {

		}

	}

}
