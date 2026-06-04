import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecordsExample {
	
	public static void main(String[] args) {
		
		Person p1 = new Person("Fayaz", 22);
		Person p2 = new Person("Sagar", 17);
		Person p3 = new Person("Krishna", 25);
		Person p4 = new Person("Priya", 15);
		
		System.out.println(p1);
		System.out.println(p2);
		
		List<Person> people = Arrays.asList(p1, p2, p3, p4);
		
		List<Person> adults = people
			.stream()
			.filter(p -> p.age() >= 18)
			.collect(Collectors.toList());
		
		System.out.println("Adults (age >= 18):");
		adults.forEach(p -> System.out.println(p));
	}
	
}

record Person(String name, int age) {}
