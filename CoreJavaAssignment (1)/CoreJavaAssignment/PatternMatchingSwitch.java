public class PatternMatchingSwitch {
	
	static void checkType(Object obj) {
		String result = switch(obj) {
			case Integer i -> "Integer with value: " + i;
			case String s -> "String with value: " + s;
			case Double d -> "Double with value: " + d;
			case null -> "Value is null";
			default -> "Unknown type: " + obj.getClass().getSimpleName();
		};
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		
		checkType(42);
		checkType("Hello");
		checkType(3.14);
		checkType(true);
		checkType(null);
	}
	
}
