import java.util.Scanner;

public class StringReversal {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a string: ");
		String input = sc.nextLine();
		
		StringBuilder sb = new StringBuilder(input);
		String reversed = sb.reverse().toString();
		
		System.out.println("Reversed: " + reversed);
		
		sc.close();
	}
	
}
