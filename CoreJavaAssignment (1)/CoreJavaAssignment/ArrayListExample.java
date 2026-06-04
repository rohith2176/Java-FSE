import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
	
	public static void main(String[] args) {
		
		ArrayList<String> studentNames = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("How many students do you want to add?");
		int count = sc.nextInt();
		sc.nextLine();
		
		for(int i=1; i<=count; i++) {
			System.out.println("Enter student name " + i + ": ");
			String name = sc.nextLine();
			studentNames.add(name);
		}
		
		System.out.println("All student names:");
		studentNames.forEach(name -> System.out.println(name));
		
		sc.close();
	}
	
}
