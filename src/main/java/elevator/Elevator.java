package elevator;

import elevator.targetset.TargetsSet;
import lombok.Getter;

public class Elevator implements IElevator, Comparable{
	private int minFloor, maxFloor;
	@Getter private int presentFloor, floorsAmount;
	
	private TargetsSet ups = new TargetsSet();
	private TargetsSet downs = new TargetsSet();
	private TargetsSet ins = new TargetsSet();
	
	ElevatorState state;
	
	/**
	 * Elevator serves on the floors from 0 to maxFloor inclusive.
	 * @param maxFloor
	 */
	public Elevator(int maxFloor) {
		super();
		
		this.maxFloor = maxFloor;
		this.minFloor = 0;
		this.state = ElevatorState.WAITING;
		
		this.floorsAmount = this.maxFloor - this.minFloor + 1;
	}
	
	/**
	 * String that visually shows states of the elevator.
	 */
	@Override
	public String toString() {
		StringBuilder answer = new StringBuilder("|");
		for(int i=0; i <= maxFloor; i++) {
			answer.append("  |");
		}
		answer.setCharAt(presentFloor*3+1, 'W');
		answer.setCharAt(presentFloor*3+2, 'W');
		for(Integer in : ins) {
			answer.setCharAt(in*3+1, '.');	
			answer.setCharAt(in*3+2, '.');
		}
		for(Integer down : downs) {
			answer.setCharAt(down*3+1, '<');
		}
		for(Integer up : ups) {
			answer.setCharAt(up*3+2, '>');
		}
		
		return answer.toString();
	}
	
	
	
	@Override
	public void step() {
		/**
		 * First clean all requests from the presentFloor. They are automatically servered.
		 */
		clean();
		/**
		 * If elevator doesn't move, it is in WAITING state, and waits for targets to serve. 
		 * If there are any targets, we set the specific mode to reach them. 
		 * 
		 */
		if(state == ElevatorState.WAITING) 
			if(anyUpper())
				state = ElevatorState.MOVING_UP;
			else if (anyLower())
				state = ElevatorState.MOVING_DOWN;
		/*
		 * If elevator is moving up, try to server "up" targets located above. 
		 */
		if(state == ElevatorState.MOVING_UP) {
			if(anyUpper()) {
				presentFloor++;
				cleanUpsAndIns();				
			}
			/*
			 * If all "up" targets are served, try moving down.
			 */
			if(!anyUpper()) {
				cleanDowns();
				if(anyLower())
					state = ElevatorState.MOVING_DOWN;
				else
					state = ElevatorState.WAITING;
			}
			/*
			 *  opposite like for state "MOVING_UP"
			 */
		}else if(state == ElevatorState.MOVING_DOWN) {
			if(anyLower()) {
				presentFloor--;
				cleanDownsAndIns();
			}
			if(!anyLower()) {
				cleanUps();
				if(anyUpper())
					state = ElevatorState.MOVING_UP;
				else
					state = ElevatorState.WAITING;
					
			}
		}
		
	}

	
	/**
	 * check if there are any targets above presetFloor to serve
	 * @return
	 */
	private boolean anyUpper() {
		return downs.anyUpper(presentFloor) || ups.anyUpper(presentFloor) || ins.anyUpper(presentFloor);
	}
	
	/**
	 * check if there are any targets below presetFloor to serve
	 * @return
	 */
	private boolean anyLower() {
		return downs.anyLower(presentFloor) || ups.anyLower(presentFloor) || ins.anyLower(presentFloor);
	}
	
	@Override
	public int getOccupation() {
		return ups.size() + downs.size() + ins.size();	
	}


	@Override
	public void addToIns(int floor) {
		ins.add(floor);		
	}

	@Override
	public ElevatorState getState() {
		return state;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Elevator) {
			Elevator other = (Elevator) o;
			return getOccupation() - other.getOccupation();
			
		}else
			return 0;
	}

	@Override
	public void addToUps(int floor) {
		ups.add(floor);
	}

	@Override
	public void addToDowns(int floor) {
		downs.add(floor);
		
	}

	@Override
	public void setState(ElevatorState state) {
		this.state = state;
		
	}

	

	/*************************
	 * CleanUp helper methods. 
	 **************************/
	
	/**
	 * Clean targets form presentFloor
	 */
	private void clean() {
		ins.remove(presentFloor);
		if(state == ElevatorState.MOVING_UP) 
			ups.remove(presentFloor);
		if(state == ElevatorState.MOVING_DOWN)
			downs.remove(presentFloor);		
	}
	
	private void cleanUps() {
		ups.remove(presentFloor);		
	}

	private void cleanDowns() {
		downs.remove(presentFloor);
		
	}

	private void cleanUpsAndIns() {
		ups.remove(presentFloor);
		ins.remove(presentFloor);
	}
	
	private void cleanDownsAndIns() {
		downs.remove(presentFloor);
		ins.remove(presentFloor);
	}
	
	
	
	
	
	
	
	
	
}
