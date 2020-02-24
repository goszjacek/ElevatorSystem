package elevator;

import java.util.LinkedList;
import java.util.Queue;

import lombok.Getter;

public class Elevator implements IElevator{
	private int minFloor, maxFloor, presentTarget;
	@Getter private int presentFloor, floorsAmount;
	private Queue<Integer> targets = new LinkedList<Integer>();
	
	private enum State {
		MOVING, WAITING
	}
	State state;

	public Elevator(int maxFloor) {
		super();
		
		this.maxFloor = maxFloor;
		this.minFloor = 0;
		this.state = State.WAITING;
		
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
	
	@Override
	public void addTarget(int floor) {
		if(floor<0 || floor > this.maxFloor)
			throw new IllegalArgumentException("Floor needs to be in range [" + this.minFloor + ", " + this.maxFloor + "]");
		
		targets.add(floor);		
	}

	@Override
	public void step() {
		setTarget();
		moveAndCheck();
		
		
	}

	private void moveAndCheck() {
		if(state == State.MOVING) {
			if(presentTarget > presentFloor)
				presentFloor += 1;
			else if(presentTarget < presentFloor)
				presentFloor -=1;
			//checkTarget
			if(presentTarget == presentFloor)
				state = State.WAITING;
		}
	}

	private void setTarget() {
		if(state == State.WAITING) {
			if(!targets.isEmpty()) {
				presentTarget = targets.poll();
				state = State.MOVING;
			}
		}
	}
	
	
	
	
	
	
}
