package demo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.IntStream;

class Lizard{
	void mange() { System.out.println("bzzzzz"); }
}

class Pizza{
	String name;
	
	Pizza(String name){
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return name.length();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Pizza)) return false;
		return ((Pizza)obj).name.equals(name);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	void mange(){
		System.out.println(name + " avalée");
	}
}

class Boite<Truc, DautresTrucs, EncoreDesTrucs>{
	
	List<Truc> data;
	
	Boite(List<Truc> theData){
		this.data = theData; 
		
		// Truc s = "hello";
	}
	
	Truc getFirst(){
		return data.size() > 0 ? data.get(0) : null;
	}
}

class TrucPrinter<Truc> {
	void print(String option, Truc...restDesArgs) {
		System.out.println(restDesArgs);
	}
}

class Vehicule extends Object {}
class Voiture extends Vehicule {}
class Citadine extends Voiture {}
class Break extends Voiture {}
class SuperBreak extends Break {}

public class DemoList {
  
	public static void main(String[] args) {		
		
		List<Object> objects = new ArrayList<>();
		objects.add(new Object());
		
		
		List<? super String> list = objects;
		list.add("A");
		list.add("B");
		
		
		for(Object s : list) {
			
		}
		
		
		List<? super Break> l1 = new ArrayList<>();
		l1.add(new Break());
		l1.add(new SuperBreak());
		//l1.add(new Citadine()); // KO
		//l1.add(new Voiture()); // KO
		//l1.add(new Vehicule()); // KO
		
		/*
		 * You can't add any object to List<? extends T>
		 *  because you can't guarantee what kind of List
		 *   it is really pointing to, so you can't guarantee 
		 *   that the object is allowed in that List. 
		 *   The only "guarantee" is that you can only read from it 
		 *   and you'll get a T or subclass of T.
		 * */
		List<SuperBreak> superBreaks = Arrays.asList(new SuperBreak());
		
		List<? extends Break> l2 = superBreaks;
		//l2.add(new Break()); // KO
		//l2.add(new SuperBreak()); // KO
		//l2.add(new Citadine()); // KO
		//l2.add(new Voiture()); // KO
		//l2.add(new Vehicule()); // KO
	}
	
	public static void demoIterators() {
		
		List<Integer> myNumbers = new ArrayList<>();
		
		myNumbers.add(0);
		myNumbers.add(1);
		myNumbers.add(2);
		myNumbers.add(3);
		
		/*
		for(Integer i : myNumbers) {
			if(i == 0) myNumbers.remove(i);
		}
		*/
		
		System.out.println(myNumbers);
		
		ListIterator<Integer> it = myNumbers.listIterator();		
		
		while(it.hasNext()) {
			System.out.println("hi :)");
			Integer i = it.next();
			if(i == 0) it.remove();
		}
		
		System.out.println(myNumbers);
		// [1,2,3]
		
		// myNumbers = [1,2,3]
		ListIterator<Integer> it2 = myNumbers.listIterator(0);
		
		while(it2.hasNext()) {
			System.out.println(it2.next());
		}
		
		// myNumbers = [1,2,3]
		ListIterator<Integer> it3 = myNumbers.listIterator();
		/*
		 * boucle infinie
		 
		while(it3.hasNext()) {
			System.out.println(it3.next());
			if(it3.hasPrevious())
				System.out.println(it3.previous());
		}
		*/
		
		while(it3.hasNext()) {			
			System.out.println(it3.next());
			it3.remove();
		}
		System.out.println(myNumbers);
	}
	
	public static void demoBasicSuperVSExtends() {
		
		/*
		<? extends E> defines E as the upper bound: "This can be cast to E".
		<? super E> defines E as the lower bound: "E can be cast to this."
		 */
		
		List<? super Voiture> classesFillesDeVoiture = new ArrayList<>(); // "E can be cast to this."
		List<? extends Voiture> classesMeresDeVoiture = new ArrayList<>(); // "This can be cast to E".
		
		classesFillesDeVoiture.add(new Break());
		classesFillesDeVoiture.add(new Citadine());
		classesFillesDeVoiture.add(new Voiture());
		
		// classesFillesDeVoiture.add(new Vehicule()); // KO
		
		List<? super Number> numbers = new ArrayList<Number>();
		numbers.add(123);
		numbers.add(Double.valueOf(123));
		numbers.add(Integer.valueOf(123));
		numbers.add(Long.valueOf('4'));
		
		
	}
	
	public static void demoBaseGenerics() {		
		Boite<Pizza, Lizard, String> boitesDePizzas = new Boite<>(Arrays.asList(new Pizza("reine"), new Pizza("chorizo")));
		Pizza unePizza = boitesDePizzas.getFirst();
		System.out.println(unePizza); // reine		
		
		Boite<String, Object, Double> boitesDeStrings = new Boite<>(Arrays.asList("hello", "world"));
		String uneString = boitesDeStrings.getFirst();
		System.out.println(uneString); // hello
		
		TrucPrinter<String> printer = new TrucPrinter<>();
		printer.print("UPPERCASE", "String 1", "String 2");
	}	
	
	public static void demoBaseStreams () {		
		int[] numbers = {3,2,1};		
		Arrays.stream(numbers).map(i -> i * 2).forEach(System.out::println); // 6 4 2
		Arrays.stream(numbers).map(i -> i * 2).sorted().forEach(System.out::println); // 2 4 6		
		
		String s = "Hello java";
		s.chars().forEach(System.out::println);
		
		IntStream.of(1,2,3).forEach(System.out::println);
		
		Pizza pizzas[] = { new Pizza("chorizo"), new Pizza("montagnarde") };
		
		//Arrays.stream(pizzas).forEach(Lizard::mange); // compile pas
		//Arrays.stream(pizzas).forEach(Pizza.mange); // compile pas
		
		Arrays.stream(numbers)
			.map(i -> i * 2)
			.sorted()
			.forEach(System.out::println);
	

		Arrays.stream(numbers)
			.map(i -> {
				if(i > 2) {
					return i + 4;
				}
				return 5 * i;
			})
			.sorted()
			.forEach(System.out::println);		
	}
	
    public static void demoDeque() {
    	Deque<Integer> deq = new ArrayDeque<>();
    	deq.add(1);
    	deq.add(2);
    	deq.add(3);
    	    	
    	System.out.println(deq.poll()); 	// 1
    	System.out.println(deq.remove()); 	// 2
    	System.out.println(deq.remove()); 	// 3
    	System.out.println(deq.remove()); 	// booom NoSuchElementException
    	
    }
    
    public static void demoMaps() {
		Map<Pizza,Integer> stockPizzas = new HashMap<>();
		
		Pizza p1 = new Pizza("saumon");
		Pizza p2 = new Pizza("reine");
		Pizza p3 = new Pizza("reine");
		
		stockPizzas.put(p1, 1);
		stockPizzas.put(p2, 2);
		stockPizzas.put(p3, 3);
		
		System.out.println(stockPizzas.get(p1)); // 1
		System.out.println(stockPizzas.get(p2)); // 3
		System.out.println(stockPizzas.get(p3)); // 3

		System.out.println(stockPizzas.values()); // [1,3]
		System.out.println(stockPizzas.keySet()); // [saumon, reine]
		System.out.println(stockPizzas.values().size()); // 2
		
		System.out.println(stockPizzas.entrySet());
		
		stockPizzas.remove(new Pizza("saumon")); 
		System.out.println(stockPizzas.values().size()); // 1		

		stockPizzas.remove(new Pizza("saumon")); 
		System.out.println(stockPizzas.values().size()); // 1
		
		stockPizzas.remove(null); 
		System.out.println(stockPizzas.values().size()); // 1
		
		stockPizzas.put(null, 1234);
		System.out.println(stockPizzas.values().size()); // 2
		
		System.out.println(stockPizzas.get(null)); // 1234
		
		for(Entry<Pizza,Integer> e : stockPizzas.entrySet()) {
			Pizza p = e.getKey();
			Integer stock = e.getValue();
		}
	}
	
	public static void customSet() {		
		Set<Pizza> myPizzas = new HashSet<>();
		
		Pizza p1 = new Pizza("saumon");
		Pizza p2 = new Pizza("reine");
		Pizza p3 = new Pizza("reine");
		
		myPizzas.add(p1);
		myPizzas.add(p2);
		myPizzas.add(p3);
		
		// Object.equals => x == y
		System.out.println(myPizzas); 

		System.out.println(myPizzas.size()); // 3
		
	}
	
	public static void demoSet() {		
		Set<Integer> myNumbers = new HashSet<>();
		
		Integer i = 12;
		
		myNumbers.add(i);		
		myNumbers.add(i);	
		
		System.out.println(myNumbers.size()); // 1
		
		myNumbers.remove(i);

		System.out.println(myNumbers.size()); // 0
	}
	
	public static void demoListRemove() {		
		List<Integer> myNumbers = new ArrayList<>();
		
		Integer i = 12;
		
		myNumbers.add(i);		
		myNumbers.add(i);	
		
		System.out.println(myNumbers.size()); // 2
		
		myNumbers.remove(i);

		System.out.println(myNumbers.size()); // 1
	}
	
	
	
	
	static void attentionAuxTaillesFixes() {
		List<String> myData = Arrays.asList("hello", "world");
		System.out.println(myData); // ? object@2134  ou  [hello, world]
		System.out.println(myData.toString()); // ? object@2134  ou  [hello, world]
		
		String s ="toto";
		myData.add(s); // booom
		System.out.println(myData.size()); // ???
	}
	
	static void attentionRemove() {
		Integer i = 123489;
		
		List<Integer> myNumbers = new ArrayList<>();
				
		myNumbers.add(123489);		
		System.out.println(myNumbers.size()); // 1
		
		//myNumbers.remove(123); // int index 
		myNumbers.remove(i);   // Object o
		
		System.out.println(myNumbers.size()); // 0
	}

}
