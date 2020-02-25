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

	public Elevator(int maxFloor) {
		super();
		
		this.maxFloor = maxFloor;
		this.minFloor = 0;
		this.state = ElevatorState.WAITING;
		
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
		clean();
		if(state == ElevatorState.WAITING) 
			if(anyUpper())
				state = ElevatorState.MOVING_UP;
			else if (anyLower())
				state = ElevatorState.MOVING_DOWN;
		
		if(state == ElevatorState.MOVING_UP) {
			if(anyUpper()) {
				presentFloor++;
				cleanUpsAndIns();				
			}
			if(!anyUpper()) {
				cleanDowns();
				if(anyLower())
					state = ElevatorState.MOVING_DOWN;
				else
					state = ElevatorState.WAITING;
			}
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
	

	private boolean anyUpper() {
		return downs.anyUpper(presentFloor) || ups.anyUpper(presentFloor) || ins.anyUpper(presentFloor);
	}
	
	private boolean anyLower() {
		return downs.anyLower(presentFloor) || ups.anyLower(presentFloor) || ins.anyLower(presentFloor);
	}

	@Override
	public int getOccupation() {
		return ups.size() + downs.size() + ins.size();	
	}

	@Override
	public void clean() {
		ins.remove(presentFloor);
		if(state == ElevatorState.MOVING_UP) 
			ups.remove(presentFloor);
		if(state == ElevatorState.MOVING_DOWN)
			downs.remove(presentFloor);		
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

	

	
	
	
	
	
	
	
	
	
}
