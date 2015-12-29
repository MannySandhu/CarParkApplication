package CarParkEntranceClients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Entrance client one class
 */

public class EntranceClientOne {
	
	
	// Run the client
	public static void main(String [] args) throws IOException{
		
		// Create the client socket
		Socket clientSocket = null;
		String clientName = "Entrance 1";
		int clientPort = 4545;
		String serverName = "localhost";
		
		// I/O
		PrintWriter outBound = null;
		BufferedReader inBound = null;
		
		// Start up the client on the clientPort
		try{
			clientSocket = new Socket(serverName, clientPort);
			outBound = new PrintWriter(clientSocket.getOutputStream(), true);
			inBound = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch(UnknownHostException e){
			System.err.println("Don't know about host: localhost ");
			System.exit(1);
		}
		catch(IOException e){
			System.err.println("Couldn't get I/O for the connection to "+ clientPort);
			System.exit(1);
		}
		
		// Take console input
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		String fromServer, fromUser;
		
		System.out.println("Initialised " + clientName + " client and I/O connections.");
		/*
		 * On key press the client communicates with the server,
		 * a key press indicates the arrival of a car that requests
		 * to park on one of the floors, the request is sent to
		 * the server and the client waits for a response.
		 * 
		 */
		
		// While the client is live listen for event
		while(true){
			fromUser = userInput.readLine();
			if(fromUser != null){
				
				// Send client input to server
				outBound.println(fromUser);
			}
			
			// Read and print server response to client window
			fromServer = inBound.readLine();
			System.out.println(serverName+":" + fromServer);
		}
	}

}
