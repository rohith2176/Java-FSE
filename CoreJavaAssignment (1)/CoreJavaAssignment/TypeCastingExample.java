public class TypeCastingExample {
	
	public static void main(String[] args) {
		
		// double to int (explicit casting)
		double d = 9.99;
		int i = (int) d;
		System.out.println("double value: " + d);
		System.out.println("After casting to int: " + i);
		
		// int to double (implicit casting)
		int num = 50;
		double result = num;
		System.out.println("int value: " + num);
		System.out.println("After casting to double: " + result);
	}
	
}
