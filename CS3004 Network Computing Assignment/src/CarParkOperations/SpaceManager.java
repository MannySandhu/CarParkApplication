package CarParkOperations;
import AdvancedCarParkServer.*;

import java.util.Scanner;

/*
 * Class handles the spaces in the car park
 */
public class SpaceManager {
	
	/*
	public static void main(String [] args){
	
		Scanner scan = new Scanner(System.in);
		SpaceManager sm = new SpaceManager();
		
		System.out.println("Running tests...");
		String input = "";
			
		while(input != null){
			
			input = scan.nextLine();
			
			switch(input){
			
			case "e":
				
				System.out.println("");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("----------------");
				sm.parkInSpace();
				System.out.println("----------------");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("");
				break;
				
			case "e1":
				System.out.println("");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("----------------");
				sm.leaveSpace("Exit_1");
				System.out.println("----------------");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("");
				break;
				
			case "e2":
				System.out.println("");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("----------------");
				sm.leaveSpace("Exit_2");
				System.out.println("----------------");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("");
				break;
				
			case "e3":
				System.out.println("");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("----------------");
				sm.leaveSpace("Exit_3");
				System.out.println("----------------");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("");
				break;
				
			case "e4":
				System.out.println("");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("----------------");
				sm.leaveSpace("Exit_4");
				System.out.println("----------------");
				sm.groundFloor.getCurrentSpace(true);
				sm.firstFloor.getCurrentSpace(true);
				System.out.println("");
				break;
			}	
		}
		
	}
	*/
	
		
	private Floor groundFloor, firstFloor;
	
	public SpaceManager(Floor groundFloor, Floor firstFloor){
		this.groundFloor = groundFloor;
		this.firstFloor = firstFloor;
	}
	
	/*
	 * 0=ground
	 * 1=first
	 * 9=no space
	 */
	public int checkSpace(){
		int code = 9;
		
		if(groundFloor.getSpace() > 0){
			code = 0;
			
		}else{
			if(firstFloor.getSpace() > 0){
				code = 1;
				
			}
		}
		return code;
	}
	
	public void parkInSpace(){
		if(checkSpace() == 0){
			groundFloor.decrementSpace();
		}
		else if(checkSpace() == 1){
			firstFloor.decrementSpace();
		}
	}
	
	public void leaveSpace(String clientID){
		
		if(clientID.equals("Exit_1") || clientID.equals("Exit_2")){
			
			// Exit from the ground floor exits
			if(groundFloor.getSpace() < 20){
				groundFloor.incrementSpace();
			}
			
		}else if(clientID.equals("Exit_3") || clientID.equals("Exit_4")){
			
			// Exit from the first floor exits
			if(firstFloor.getSpace() < 20){
				firstFloor.incrementSpace();
			}
		}
		
	}
	
	/* Report on the current status of space in the car park
	 * Used in the CPMSServer class, output to server window to monitor
	 * the state of the floors
	 */
	public void reportSpace(){
		System.out.println("");
		System.out.println("-----------------------------------------");
		System.out.println("Ground Floor Spaces: "+ groundFloor.getSpace());
		System.out.println("First Floor Spaces: "+ firstFloor.getSpace());
		System.out.println("-----------------------------------------");
		System.out.println("");
	}
		
}
