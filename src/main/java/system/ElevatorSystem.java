package system;

import elevator.Elevator;

public class ElevatorSystem implements IElevatorSystem {
	Elevator elevator;

	public ElevatorSystem(int maxFloor) {
		super();
		elevator = new Elevator(maxFloor);
	}

	@Override
	public String toString() {
		return elevator.toString();
	}

	@Override
	public void pickUp(int floor) {
		elevator.addTarget(floor);
		
	}

	@Override
	public void step() {
		elevator.step();
		print();
	}
	
	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public int getPresentFloor() {
		return elevator.getPresentFloor();		
	}
	


	
	
	
	
}
