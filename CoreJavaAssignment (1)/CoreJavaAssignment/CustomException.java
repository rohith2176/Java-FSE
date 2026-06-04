import java.util.Scanner;

public class CustomException {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Enter your age: ");
			int age = sc.nextInt();
			
			if(age < 18) {
				throw new InvalidAgeException("Age must be 18 or above. Entered age: " + age);
			}
			System.out.println("Access granted. Welcome!");
			
		}catch(InvalidAgeException e) {
			System.out.println("InvalidAgeException: " + e.getMessage());
		}finally {
			sc.close();
		}
	}
	
}

class InvalidAgeException extends RuntimeException {
	
	public InvalidAgeException(String message) {
		super(message);
	}
	
}
