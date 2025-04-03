package demo;

class M {
	private int num1 = 100;
	
	class N {
		private int num2 = 200;
	}
	
	public static void main(String[] args) {
		M outer = new M();
		M.N inner = outer.new N();
		System.out.println(outer.num1 + inner.num2);
	}
}

class Cat {
	// java adds
	public Cat() {
		super();
	}
	
	Cat(int age){
		
	}
}

public class HidingConstructors {

	public static void main(String... args) {
		System.out.println(new Cat());
	}
}
