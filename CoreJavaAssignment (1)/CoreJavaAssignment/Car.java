public class Car {
	
	String make;
	String model;
	int year;
	
	Car(String make, String model, int year) {
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
	void displayDetails() {
		System.out.println("Make: " + make);
		System.out.println("Model: " + model);
		System.out.println("Year: " + year);
	}
	
	public static void main(String[] args) {
		
		Car car1 = new Car("Toyota", "Camry", 2022);
		Car car2 = new Car("Honda", "Civic", 2023);
		
		System.out.println("Car 1 Details:");
		car1.displayDetails();
		
		System.out.println("Car 2 Details:");
		car2.displayDetails();
	}
	
}
