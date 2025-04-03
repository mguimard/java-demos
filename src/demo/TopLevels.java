package demo;

// top level classes
// 1 seule classe public, meme nom du fichier.java
// 1 classe top level ne peut pas être private; ni protected

/*
 // ko
public class Animal{
	int age;
}
protected class Dog {}
*/

public class TopLevels {
	
	// inner classes	
	public class C1 {}
	private class C2 {}
	protected class C3 {}
	class C4 {}	
	
	static public class S1{}
	static private class S2{}
	static protected class S3{}
	static class S4{}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		
		Robot r = new Robot();
		S1 c1 = new S1();
		
	}

}
