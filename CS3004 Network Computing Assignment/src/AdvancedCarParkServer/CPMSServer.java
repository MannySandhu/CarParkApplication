package AdvancedCarParkServer;
import CarParkOperations.*;
import CarParkExitClients.*;
import CarParkEntranceClients.*;
import java.io.*;
import java.net.*;

/*
 * Server class for the Car Park Management System (CPMS)
 */

public class CPMSServer {
	
	public static void main(String [] args) throws IOException {
		
		// Create the server socket
		ServerSocket CPMSServerSocket = null;
		String serverName = "CPMS Server";
		int serverPort = 4545;
		
		// If the server is live
		boolean listening = true;
		
		// Start up the server on serverPort
		try{
			CPMSServerSocket = new ServerSocket(serverPort);
		}
		catch(IOException e){
			System.err.println("Could not start "+serverName+" on port: "+serverPort);
			System.exit(-1);
		}
		System.out.println(serverName+" is running:");
		
		// Print the spaces available to the server window
		
		
		
		while(listening){
			// Run the two entrance client threads
			new EntranceClientOne(CPMSServerSocket,"ENC_1");
			new EntranceClientTwo(CPMSServerSocket,"ENC_2");
			
			// Run the two ground floor exit client threads
			new ExitClientOne(CPMSServerSocket, "EXC_1");
			new ExitClientTwo(CPMSServerSocket, "EXC_2");
			
			// Run the two first floor exit client threads
			new ExitClientThree(CPMSServerSocket, "EXC_3");
			new ExitClientFour(CPMSServerSocket, "EXC_4");
			
		}
		
		// Close connection
		CPMSServerSocket.close();
	}
}
