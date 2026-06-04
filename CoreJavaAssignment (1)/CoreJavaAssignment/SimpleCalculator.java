import java.util.Scanner;

public class SimpleCalculator {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter first number: ");
		double num1 = sc.nextDouble();
		
		System.out.println("Enter second number: ");
		double num2 = sc.nextDouble();
		
		System.out.println("Choose operation: 1-Add  2-Subtract  3-Multiply  4-Divide");
		int choice = sc.nextInt();
		
		double result = 0;
		
		if(choice==1) {
			result = num1 + num2;
			System.out.println("Result: " + result);
		}else if(choice==2) {
			result = num1 - num2;
			System.out.println("Result: " + result);
		}else if(choice==3) {
			result = num1 * num2;
			System.out.println("Result: " + result);
		}else if(choice==4) {
			if(num2==0) {
				System.out.println("Cannot divide by zero");
			}else {
				result = num1 / num2;
				System.out.println("Result: " + result);
			}
		}else {
			System.out.println("Invalid choice");
		}
		
		sc.close();
	}
	
}
