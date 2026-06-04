import java.lang.reflect.Method;

public class ReflectionExample {
	
	public static void main(String[] args) {
		
		try {
			// load class dynamically
			Class<?> clazz = Class.forName("SampleClass");
			
			System.out.println("Class: " + clazz.getName());
			System.out.println("Methods:");
			
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods) {
				System.out.println("  " + method.getName() + " - Parameters: " + method.getParameterCount());
			}
			
			// create instance and invoke method
			Object obj = clazz.getDeclaredConstructor().newInstance();
			Method greetMethod = clazz.getDeclaredMethod("greet", String.class);
			String result = (String) greetMethod.invoke(obj, "Fayaz");
			System.out.println("Invoked greet(): " + result);
			
		}catch(Exception e) {
			System.out.println("Reflection error: " + e.getMessage());
		}
	}
	
}

class SampleClass {
	
	public String greet(String name) {
		return "Hello, " + name + "!";
	}
	
	public int add(int a, int b) {
		return a + b;
	}
	
}
