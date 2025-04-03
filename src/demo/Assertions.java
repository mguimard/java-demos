package demo;

class MyLib {

	/**
	 * This method ....
	 * 
	 * @param s, a string, cannot be null.
	 * @return nothing.
	 */
	static void doSomething(String s) {
		assert s != null : "Booh bad developer, s cannot be null, read the doc";
		System.out.println(s.concat(" world"));
	}
}

public class Assertions {

	public static void main(String[] args) {

		MyLib.doSomething("hello");

		MyLib.doSomething("hello");

		MyLib.doSomething(null);

	}

}
