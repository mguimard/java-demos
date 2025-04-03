package demo;

abstract class MessagePrinter{
	public abstract void print(String message);
}

public class Anonymous {
	
	private static abstract class TaskRunner{
		public abstract void afterRun();

		public void run() {
			// ....
			/// async ....
			
			afterRun();
		}
	}
	
	public static void main(String... args) {
				
		TaskRunner myRunner = new TaskRunner() {			
			@Override
			public void afterRun() {			
				System.out.println("This is over");				
			}
		};
		
		myRunner.run();		
		
		MessagePrinter myCustomPrinter = new MessagePrinter() {			
			@Override
			public void print(String message) {
				System.out.println("PRINT1: " + message);				
			}
		};

		MessagePrinter myCustomPrinter2 = new MessagePrinter() {			
			@Override
			public void print(String message) {
				System.out.println("PRINT2: " + message);				
			}
		};		
		
		myCustomPrinter.print("hello world");
		
	}
	
}
