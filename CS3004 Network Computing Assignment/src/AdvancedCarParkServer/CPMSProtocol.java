package AdvancedCarParkServer;
import CarParkOperations.FloorManager;

/*
 * Class handles locking and floor spaces by working with the
 * floor space data 
 */
public class CPMSProtocol {
	
	private boolean accessing = false; // True if a thread has a lock, false otherwise
	private int threadsWaiting = 0; // Number of waiting writers
	
	// Variable holds the floor space data to be accessed
	private FloorManager floorSpace;
	
	// Constructor
	CPMSProtocol(FloorManager floorSpaceData) {
		floorSpace = floorSpaceData;
	}
	
	
	// Acquire a lock
	public synchronized void acquireLock() throws InterruptedException{
		Thread clientID = Thread.currentThread(); // Get the thread clientID
		System.out.println();
	    System.out.println(clientID.getName()+" is attempting to acquire a lock.");	
	    ++threadsWaiting;
		  
	    while (accessing) {  // While someone else is accessing or threadsWaiting > 0
	    System.out.println(clientID.getName()+" waiting to get a lock as someone else is accessing...");
		// Wait for the lock to be released - see releaseLock() below
		  wait();
		}
	      
		// Nobody has got a lock so get one
		--threadsWaiting;
		accessing = true;
		System.out.println(clientID.getName()+" got a lock."); 
	}
	
	// Release the lock
	public synchronized void releaseLock(){
		// Release the lock and tell everyone
	    accessing = false;
	    notifyAll();
	    Thread clientID = Thread.currentThread(); // Get the thread clientID
	    System.out.println(clientID.getName()+" released a lock.");
	}
	
	// Method reports the current status of the spaces
	public void CarParkSpaceState() {
		floorSpace.reportSpace();
	}
	
	// Process the input sent to the CPMS server
	public synchronized String processInput(String input){
		String output = null;
		String clientID = Thread.currentThread().getName();
		
		if(input.equalsIgnoreCase("E")){
			
			switch(clientID){
			
			case "Entrance_1":
				output = floorSpace.occupySpace();
				break;
				
			case "Entrance_2":
				output = floorSpace.occupySpace();
				break;
				
			case "Exit_1":
				output = floorSpace.leaveSpace(clientID);
				break;
				
			case "Exit_2":
				output = floorSpace.leaveSpace(clientID);
				break;
	
			case "Exit_3":
				output = floorSpace.leaveSpace(clientID);
				break;
				
			case "Exit_4":
				output = floorSpace.leaveSpace(clientID);
				break;
			}
			
			// Report the changes to the spaces
			floorSpace.reportSpace();
		}
			
		return output;
	}

}
