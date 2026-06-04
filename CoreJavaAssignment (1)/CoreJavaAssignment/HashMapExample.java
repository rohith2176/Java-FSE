import java.util.HashMap;
import java.util.Scanner;

public class HashMapExample {
	
	public static void main(String[] args) {
		
		HashMap<Integer, String> studentMap = new HashMap<Integer, String>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("How many students do you want to add?");
		int count = sc.nextInt();
		
		for(int i=1; i<=count; i++) {
			System.out.println("Enter student ID: ");
			int id = sc.nextInt();
			System.out.println("Enter student name: ");
			String name = sc.next();
			studentMap.put(id, name);
		}
		
		System.out.println("Enter ID to search: ");
		int searchId = sc.nextInt();
		
		if(studentMap.containsKey(searchId)) {
			System.out.println("Student found: " + studentMap.get(searchId));
		}else {
			System.out.println("No student found with ID: " + searchId);
		}
		
		sc.close();
	}
	
}
