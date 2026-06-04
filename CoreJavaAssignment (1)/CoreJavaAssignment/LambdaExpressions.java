import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpressions {
	
	public static void main(String[] args) {
		
		List<String> names = new ArrayList<String>();
		names.add("Sagar");
		names.add("Fayaz");
		names.add("Krishna");
		names.add("Hari");
		names.add("Priya");
		
		System.out.println("Before sorting: " + names);
		
		Collections.sort(names, (a, b) -> a.compareTo(b));
		
		System.out.println("After sorting: " + names);
	}
	
}
