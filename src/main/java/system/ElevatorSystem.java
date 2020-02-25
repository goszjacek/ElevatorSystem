package system;

import java.util.ArrayList;
import java.util.List;

import elevator.Elevator;
import elevator.IElevator;

public class ElevatorSystem implements IElevatorSystem {
	private List<IElevator> elevators;
	int maxFloor;

	public ElevatorSystem(int maxFloor, int elevatorsAmount) {
		super();
		
		if(maxFloor < 1)
			throw new IllegalArgumentException("maxFloor < 1");
		if(elevatorsAmount < 1 || elevatorsAmount > 16)
			throw new IllegalArgumentException("elevatorsAmount not in [1,16]");
		
		this.maxFloor = maxFloor;
		elevators = new ArrayList<IElevator>();
		for(int i = 0; i < elevatorsAmount; i++) {
			elevators.add(new Elevator(maxFloor));
		}
	}
	
	public ElevatorSystem(int maxFloor) {
		this(maxFloor, 1);
	}

	@Override
	public String toString() {
		String answer = "";
		for(IElevator e : elevators) {
			answer += e.toString() + "\n";
		}
		return answer;
	}

	@Override
	public void outerPickUp(int floor) {
		getLeastOccupied().addTarget(floor);
	}

	@Override
	public void innerPickUp(int elevatorId, int floor) {
		validateId(elevatorId);
		elevators.get(elevatorId).addTarget(floor);
		
	}
	

	private void validateId(int elevatorId) {
		if(elevatorId < 0 || elevatorId > elevators.size())
			throw new IllegalArgumentException("Elevator id needs to be between 0 and " + (elevators.size()-1));
		
	}

	private IElevator getLeastOccupied() {
		if(elevators.size() == 1)
			return elevators.get(0);
		else {
			IElevator minElevator = elevators.get(0);
			for(IElevator e : elevators) {
				if(e.getOccupation() < minElevator.getOccupation()) {
					minElevator = e;
				}
			}
			return minElevator;	
		}
	}

	@Override
	public void step() {
		elevators.forEach((e) -> e.step());
		print();
	}
	
	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public int getPresentFloor() {
		return this.getPresentFloor(0);		
	}
	
	@Override
	public int getPresentFloor(int elevatorId) {
		return elevators.get(elevatorId).getPresentFloor();		
	}

	


	
	
	
	
}
