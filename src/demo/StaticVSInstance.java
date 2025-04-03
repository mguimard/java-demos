package demo;

class Robot {	
	
	// static = éxécuté une seule fois au chargement de la classe par la JVM
	static int i = 0;
	static float x = 10 / i; // ArithmeticException
	
	static
	{
		// exec une seule fois
		System.out.println("STATIC BLOC");
	}	
	
	int b; // valeur par défaut = 0
	Integer i1; 	// valeur par défaut = null
	Integer i2 = 10; 
	
	// pour chaque nouvelle instance
	{
		System.out.println("BLOC INIT 1");
		System.out.println("a=" + this.a); // ? 0
		System.out.println("b=" +this.b); // 0
		System.out.println("i1=" + this.i1); // null
		System.out.println("i2=" +this.i2); // null
	}

	{
		System.out.println("BLOC INIT 2");
	}

	int a = 7;

	public Robot() 
	{
		System.out.println("CONSTRUCTOR");
		a = 10;
		b = 12;
	}	
	
}

public class StaticVSInstance {

	public static void main(String[] args) {
		Robot r = new Robot();
		Robot r2 = new Robot();
	}
}
