package demo;

class MyWriter { 	
	int count = 10;
		
	static {
		System.out.println("OUTER STATIC");
		System.out.println(MyWriterCreator.writer); //
	}
	
	private MyWriter() {
		System.out.println("CONSTRUCTOR");
		count--;
	}
	
	private void configure(String s) {
		System.out.println("CONFIGURE");
	}
	
	public static class MyWriterCreator {
		public static MyWriter writer = new MyWriter();
		
		static {
			System.out.println("INNER STATIC");
			writer.configure("/path/to/file");
		}
	}
	
	public static MyWriter instance() {
		return MyWriterCreator.writer;
	}	
}

public class SingletonV2 {

	public static void main(String[] args) {
		MyWriter w1 = MyWriter.instance();
		MyWriter w2 = MyWriter.instance();		
		
		System.out.println(w1 == null); // false 
		System.out.println(w1 == w2); // true	
		System.out.println(w1.count); // ?
		System.out.println(w2.count); // ?

	}

}
