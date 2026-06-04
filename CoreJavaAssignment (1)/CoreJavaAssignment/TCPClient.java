import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
	
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("localhost", 5000);
			System.out.println("Connected to server");
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			
			String message;
			while(true) {
				System.out.print("Client: ");
				message = userInput.readLine();
				out.println(message);
				String reply = in.readLine();
				System.out.println("Server: " + reply);
			}
			
		}catch(Exception e) {
			System.out.println("Client error: " + e.getMessage());
		}
	}
	
}
