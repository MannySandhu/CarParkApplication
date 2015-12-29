package CarParkOperations;
/*
 * Class handles the modification of spaces on the floors 
 */
public class FloorManager {
	
	// Ground and first floor objects
	private Floor groundFloor = new Floor();
	private Floor firstFloor = new Floor();
	
	// Entrance queue 
	private int queue = 0;
	
	// Returns 0 and 1 if there are spaces, 9 otherwise
	private int checkSpace(){
		int code = 9;
		
		if(groundFloor.space > 0){
			code = 0; 
		}else 
			{
				if(firstFloor.space > 0){
					code = 1; 
				}
			}
		return code;
	}
	
	// Occupy a space on a floor
	public String occupySpace(){
		String action = "No spaces available";
		
		if(checkSpace() == 0){
			if(queue > 0){
				--queue;	
			}
			--groundFloor.space;
			action = "Parking on ground floor";
		}
		else if(checkSpace() == 1){
			if(queue > 0){
				--queue;	
			}
			--firstFloor.space;
			action = "Parking on first floor";
		}else {
			++queue;
		}
	
		return action;
	}
	
	/*
	 * Leave a space from the ground or first floor,
	 * exit used depends on which floor the car is parked
	 */
	public String leaveSpace(String clientID){
		String action = "Floor is empty";
		
		if(clientID.equals("Exit_1") || clientID.equals("Exit_2")){
			
			// Exit from the ground floor exits
			if(groundFloor.space < 20){
				++groundFloor.space;
				action = "Leaving ground floor";
			}
			
		}else if(clientID.equals("Exit_3") || clientID.equals("Exit_4")){
			
			// Exit from the first floor exits
			if(firstFloor.space < 20){
				++firstFloor.space;
				action = "Leaving first floor";
			}
		}
		return action;
	}
	
	// Report on the spaces in the car park
	public void reportSpace(){
		
		System.out.println("----------------------------");
		System.out.println("Current spaces: ");
		System.out.println("Ground floor: "+groundFloor.space);
		System.out.println("First floor: "+firstFloor.space);
		System.out.println("Current entrance queue: "+queue);
		System.out.println("----------------------------");

	}
	
	// Nested class creates a floor object
	private class Floor {
		
		// A floor has 20 spaces
		private int space = 20;
	}
}