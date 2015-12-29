package AdvancedCarParkServer;
import CarParkOperations.FloorManager; 
import java.io.IOException;
import java.net.ServerSocket;

/*
 * Server class for the Car Park Management System (CPMS)
 */

public class CPMSServer {
	
	public static void main(String [] args) throws IOException {
		
		
		/*
		 * Server holds the floor space data
		 */
		FloorManager floorSpace = new FloorManager();
		
		// Create global protocol object
		CPMSProtocol floorSpaceData = new CPMSProtocol(floorSpace);
		
		
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
		floorSpaceData.CarParkSpaceState();
		
		while(listening){
			// Run the two entrance client threads
			new CPMSThread(CPMSServerSocket.accept(), "Entrance_1", floorSpaceData).start();
			new CPMSThread(CPMSServerSocket.accept(), "Entrance_2", floorSpaceData).start();
			
			// Run the two ground floor exit client threads
			new CPMSThread(CPMSServerSocket.accept(), "Exit_1", floorSpaceData).start();
			new CPMSThread(CPMSServerSocket.accept(), "Exit_2", floorSpaceData).start();
			
			// Run the two first floor exit client threads
			new CPMSThread(CPMSServerSocket.accept(), "Exit_3", floorSpaceData).start();
			new CPMSThread(CPMSServerSocket.accept(), "Exit_4", floorSpaceData).start();
			
		}
		
		// Close connection
		CPMSServerSocket.close();
	}
}
