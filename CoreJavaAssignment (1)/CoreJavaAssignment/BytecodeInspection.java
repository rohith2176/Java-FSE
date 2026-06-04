public class BytecodeInspection {
	
	static int multiply(int a, int b) {
		return a * b;
	}
	
	static String greet(String name) {
		return "Hello, " + name;
	}
	
	public static void main(String[] args) {
		int result = multiply(5, 10);
		System.out.println("Result: " + result);
		System.out.println(greet("Fayaz"));
	}
	
}
