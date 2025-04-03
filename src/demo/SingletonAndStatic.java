package demo;

class MyReader { 
	
	public final static String message = "Hello reader";
	
	public final int bytes = 123;
	
	private static MyReader reader;
	
	private MyReader() {
		System.out.println("CONSTRUCTOR");
	}
	
	public static MyReader instance() {
		if(reader == null) {
			reader = new MyReader();
		}
		
		return reader;
	}
	
}


public class SingletonAndStatic {

	public static void main(String[] args) {
		MyReader r1 = MyReader.instance();
		MyReader r2 = MyReader.instance();
		
		System.out.println(r1.message);
		
		MyReader r3 = null; 
		
		System.out.println(r3 == null);
		System.out.println(MyReader.message);   
		System.out.println(r3.message); 		
		
		//System.out.println(MyReader.bytes);   // KO
		System.out.println(r3.bytes); 	 // NPE
		
		System.out.println("Fin de programme");
	}

}
