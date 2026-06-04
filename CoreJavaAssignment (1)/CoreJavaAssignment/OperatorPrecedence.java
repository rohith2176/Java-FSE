public class OperatorPrecedence {
	
	public static void main(String[] args) {
		
		// multiplication happens before addition
		int result1 = 10 + 5 * 2;
		System.out.println("10 + 5 * 2 = " + result1); // 20
		
		// brackets evaluated first
		int result2 = (10 + 5) * 2;
		System.out.println("(10 + 5) * 2 = " + result2); // 30
		
		// division before subtraction
		int result3 = 20 - 10 / 2;
		System.out.println("20 - 10 / 2 = " + result3); // 15
		
		// mixed operators
		int result4 = 5 + 3 * 2 - 8 / 4;
		System.out.println("5 + 3 * 2 - 8 / 4 = " + result4); // 9
		
		// modulus
		int result5 = 10 % 3 + 4 * 2;
		System.out.println("10 % 3 + 4 * 2 = " + result5); // 9
	}
	
}
