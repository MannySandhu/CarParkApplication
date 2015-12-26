package AdvancedCarParkServer;
import CarParkOperations.*;

/*
 * Class handles locking and floor spaces by working with the
 * floor space data 
 */
public class CPMSProtocol {
	
	/*
	 * Data
	 */
	private Floor groundFloor = new Floor();
	private Floor firstFloor = new Floor();
	private SpaceManager sm = new SpaceManager(groundFloor, firstFloor);
	
	private boolean accessing=false; // true a thread has a lock, false otherwise
	private int threadsWaiting=0; // number of waiting writers
	
	private double var;
	CPMSProtocol(double var) {
		this.var = var;
	}
	
	private String clientID;
	
	// Acquire a lock
	public synchronized void acquireLock() throws InterruptedException{
		 Thread me = Thread.currentThread(); // get a ref to the current thread
	        System.out.println(me.getName()+" is attempting to acquire a lock!");	
	        ++threadsWaiting;
		    while (accessing) {  // while someone else is accessing or threadsWaiting > 0
		      System.out.println(me.getName()+" waiting to get a lock as someone else is accessing...");
		      //wait for the lock to be released - see releaseLock() below
		      wait();
		    }
		    // nobody has got a lock so get one
		    --threadsWaiting;
		    accessing = true;
		    System.out.println(me.getName()+" got a lock!"); 
	}
	
	// Release the lock
	public synchronized void releaseLock(){
		//release the lock and tell everyone
	      accessing = false;
	      notifyAll();
	      Thread me = Thread.currentThread(); // get a ref to the current thread
	      System.out.println(me.getName()+" released a lock!");
	}
	
	// Process the input sent to the CPMS server
	public synchronized String processInput(String input){
		String output = null;
		clientID = Thread.currentThread().getName();
		
		if(input.equalsIgnoreCase("E")){
			
			switch(clientID){
			
			case "ENC_1":
				//output = "Car entering at: "+clientID;
				//sm.parkInSpace();
				//sm.reportSpace();
				var = var + 5;
				output = "new: "+var;
				break;
				
			case "ENC_2":
				//output = "Car entering at: "+clientID;
				//sm.parkInSpace();
				//sm.reportSpace();
				var = var -3;
				output = "new: "+var;
				break;
				
			case "EXC_1":
				
				break;
				
			case "EXC_2":
	
				break;
	
			case "EXC_3":
	
				break;
				
			case "EXC_4":
				
				break;
			}
		}
			
		return output;
	}

}
