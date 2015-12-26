package AdvancedCarParkServer;
import java.net.*;
import java.io.*;

/*
 * Thread initialises new client-server processes, a new thread
 * is created for each client when it connects to the server.
 * 
 */

public class CPMSThread extends Thread {
	
	private Socket clientSocket = null;
	private String clientID;
	private CPMSProtocol protocol;
	CPMSProtocol varObj = new CPMSProtocol(10);
	
	// Constructor for launching new client-server processes
	public CPMSThread(Socket clientSocket, String clientID){
		super(clientID);
		this.clientSocket = clientSocket;
		protocol = varObj;
	}
	
	// Define what the thread does when running
	public void run(){
		try {
			
			// Get the the
			clientID = Thread.currentThread().getName();
			System.out.println(clientID + " client thread initialising");
			
			// Create I/O
			PrintWriter outBound = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader inBound = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine, outputLine;
			
			// Protocol object
			//CPMSProtocol protocol = new CPMSProtocol();
			
			while((inputLine = inBound.readLine()) != null){
try{
				protocol.acquireLock();
				// Process client input and send response to client
				outputLine = protocol.processInput(inputLine);
				outBound.println(outputLine);
				protocol.releaseLock();
				
				// Print out to server window
				System.out.println(outputLine);
				
				if(inputLine.equalsIgnoreCase("end")){
					break;
				}
}catch(InterruptedException e){
	System.out.println("failed to get lock");
}
			}
			outBound.close();
			inBound.close();
			clientSocket.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
