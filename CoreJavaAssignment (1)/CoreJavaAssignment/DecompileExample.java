public class DecompileExample {
	
	private String name;
	private int age;
	
	public DecompileExample(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return "DecompileExample [name=" + name + ", age=" + age + "]";
	}
	
	public static void main(String[] args) {
		DecompileExample obj = new DecompileExample("Fayaz", 22);
		System.out.println(obj);
	}
	
}
