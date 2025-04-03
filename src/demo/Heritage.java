package demo;

// interdit de faire un new sur une abstract class
// interdit d'extend une final class
// interdit de baisser la visibilité d'une méthode surchargée

// implicite import par java
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

interface PeutSauter{
	default void saute() {
		System.out.println("IMPL PAR DEFAUT");
	};
}
interface PeutNager{
	/*public abstract*/ void nage();
}

// implicite extends par java
class Animal extends Object {
	void cri() { System.out.println("CRI PAR DEFAUT");}	
}

class Dog extends Animal implements PeutSauter, PeutNager {
	
	@Override
	public void nage() {
			
	}
	
	@Override
	public void cri() { super.cri(); System.out.println("WOOF");}
	
	public String toString() {
		return "je suis un instance de Dog";
	}
	
/*
 	// KO c'est final
	public void wait() {
		// ...
	}	
*/
	
	public int hashCode() {
		return 1234;
	}


}

public class Heritage {	
	public static void main(String[] args) {
	
		Animal dog1 = new Animal();
		dog1.cri(); // CRI PAR DEFAUT
		
		Animal dog2 = new Dog();
		dog2.cri(); // CRI PAR DEFAUT + WOOF		
		
		System.out.println(dog1); // ?? demo.Animal@1234586
		System.out.println(dog2); // je suis un instance de Dog
	
		//Dog dog3 = (Dog)new Animal(); // ClassCastException
		//dog3.cri();
		
	}
}
