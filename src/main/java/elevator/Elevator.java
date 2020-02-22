package elevator;

import lombok.Getter;

public class Elevator {
	private int minFloor, maxFloor;
	@Getter private int presentFloor, floorsAmount;

	public Elevator(int maxFloor) {
		super();
		this.maxFloor = maxFloor;
		this.minFloor = 0;
		
		this.floorsAmount = this.maxFloor - this.minFloor + 1; 
	}

	@Override
	public String toString() {
		StringBuilder answer = new StringBuilder("|");
		for(int i=0; i <= maxFloor; i++) {
			answer.append("  |");
		}
		answer.setCharAt(presentFloor*3+1, 'W');
		answer.setCharAt(presentFloor*3+2, 'W');
		return answer.toString();
	}
	
	
	
	
}
