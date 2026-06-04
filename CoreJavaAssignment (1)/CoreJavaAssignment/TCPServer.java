import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			System.out.println("Server started. Waiting for client...");
			
			Socket socket = serverSocket.accept();
			System.out.println("Client connected");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			
			String message;
			while((message = in.readLine()) != null) {
				System.out.println("Client: " + message);
				System.out.print("Server: ");
				String reply = userInput.readLine();
				out.println(reply);
			}
			
			socket.close();
			serverSocket.close();
			
		}catch(Exception e) {
			System.out.println("Server error: " + e.getMessage());
		}
	}
	
}
