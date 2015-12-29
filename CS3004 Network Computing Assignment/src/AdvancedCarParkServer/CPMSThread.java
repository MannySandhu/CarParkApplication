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
	private CPMSProtocol floorSpaceData;
	
	// Constructor for launching new client-server processes
	public CPMSThread(Socket clientSocket, String clientID, CPMSProtocol floorSpaceData){
		super(clientID);
		this.clientSocket = clientSocket;
		this.floorSpaceData = floorSpaceData;
		
	}
	
	// Define what the thread does when running
	public void run(){
		try {
			
			// Get the current threads clientID
			clientID = Thread.currentThread().getName();
			System.out.println(clientID + " client thread initialising");
			
			// Create I/O
			PrintWriter outBound = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader inBound = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine, outputLine;
			
			
			while((inputLine = inBound.readLine()) != null){
				try{
					
				// Get a lock	
				floorSpaceData.acquireLock();
				// Process client input and send response to client
				outputLine = floorSpaceData.processInput(inputLine);
				// Do action
				outBound.println(outputLine);
				floorSpaceData.releaseLock();
				
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
