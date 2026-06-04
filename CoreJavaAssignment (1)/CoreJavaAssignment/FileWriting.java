import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriting {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter text to write to file: ");
		String text = sc.nextLine();
		
		try(FileWriter fw = new FileWriter("output.txt")) {
			fw.write(text);
			System.out.println("Data written to output.txt successfully");
		}catch(IOException e) {
			System.out.println("Error writing file: " + e.getMessage());
		}
		
		sc.close();
	}
	
}
