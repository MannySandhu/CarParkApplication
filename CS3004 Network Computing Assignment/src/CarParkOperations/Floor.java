package CarParkOperations;

public class Floor {
	
	private int space = 20;
	
	public int getSpace(){
		return space;
	}
	
	public void incrementSpace(){
		++space;
	}
	
	public void decrementSpace(){
		--space;
	}
	
	public int getCurrentSpace(boolean report){
		if(report == true){
			System.out.println("Current space is: " + space);
		}
		return space;
	}
	
}