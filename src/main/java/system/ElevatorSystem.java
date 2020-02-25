package system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import elevator.Elevator;
import elevator.ElevatorState;
import elevator.IElevator;

public class ElevatorSystem implements IElevatorSystem {
	private List<IElevator> elevators;
	int maxFloor;
	
	/**
	 * The system initialises elevatorsAmount elevators with last floor numbered maxFloor
	 * @param maxFloor
	 * @param elevatorsAmount
	 */
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
	
	/**
	 * Visualise all elevators. 
	 */
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
		this.outerPickUp(floor, true);
	}
	


	
	@Override
	public void outerPickUp(int floor, boolean up) {
		validateFloor(floor);
		validateFirstOrLast(floor,up);
		
		boolean served = false;
		/*
		 * Try to use elevators that are moving in direction of `floor` passed as argument. 
		 */
		if(up) {
			Optional<IElevator> optionalMovingUp = firstMovingUpBelow(floor);
			if(optionalMovingUp.isPresent()) {
				optionalMovingUp.get().addToUps(floor);
				served = true;
			}
		}else {
			Optional<IElevator> optionalMovingDown = firstMovingDownAbove(floor);
			if(optionalMovingDown.isPresent()) {
				optionalMovingDown.get().addToDowns(floor);
				served = true;
			}
			
		}
		
		/*
		 * If usage of any moving elevator wasn't possible, try other elevators. 
		 */
		if(!served) {
			/*
			 * If there is any elevators waiting for targets, use it.
			 */
			Optional<IElevator> optionalWaiting = optionalWaiting();
			if(optionalWaiting.isPresent()) {
				IElevator chosen = optionalWaiting.get();
				if(up) 
					chosen.addToUps(floor);			
				else 
					chosen.addToDowns(floor);
				if(floor > chosen.getPresentFloor())
					chosen.setState(ElevatorState.MOVING_UP);
				else
					chosen.setState(ElevatorState.MOVING_DOWN);
						
					
			}else {
				/*
				 * If all elevators are occupied, use the elevator that is the least occupied.
				 */
				Optional<IElevator> leastOccupied = leastOccupied();
				if(leastOccupied.isEmpty())
					throw new RuntimeException("No Elevators available!!!");
				else
					if(up)
						leastOccupied.get().addToUps(floor);
					else
						leastOccupied.get().addToDowns(floor);
			}
		}
		
			
	
	}
	
	/**
	 * Get first elevator that is above `floor` and is moving down.
	 * @param floor
	 * @return
	 */
	private Optional<IElevator> firstMovingDownAbove(int floor) {
		return elevators
				.stream()
				.filter(elevator -> elevator.getState() == ElevatorState.MOVING_DOWN)
				.filter(elevator -> elevator.getPresentFloor() > floor)
				.sorted()
				.findFirst();
	}

	/**
	 * Get the elevator that is the least occupied. 
	 * @return
	 */
	private Optional<IElevator> leastOccupied() {
		return elevators
				.stream()
				.sorted()
				.findFirst();
	}
	
	/**
	 * Get any elevator that is waiting.
	 * @return
	 */
	private Optional<IElevator> optionalWaiting() {
		return elevators
				.stream()
				.filter(elevator -> elevator.getState() == ElevatorState.WAITING)
				.findFirst();
	}

	/**
	 * Get first elevator that is below `floor` and is moving up.
	 * @param floor
	 * @return
	 */
	private Optional<IElevator> firstMovingUpBelow(int floor) {
		return elevators
				.stream()
				.filter(elevator -> elevator.getState() == ElevatorState.MOVING_UP)
				.filter(elevator -> elevator.getPresentFloor() < floor)
				.sorted()
				.findFirst();
	}

	

	@Override
	public void innerPickUp(int elevatorId, int floor) {
		validateId(elevatorId);
		elevators.get(elevatorId).addToIns(floor);
		
	}
	
	
	@Override
	public void step() {
		elevators.forEach((e) -> e.step());
		print();
	}
	
	/**
	 * Visualise ElevatorSystem.
	 */
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

	@Override
	public Iterable<?> elevatorsLocations() {
		List<Integer> locations = new ArrayList<Integer>();
		for(IElevator e : elevators) {
			locations.add(e.getPresentFloor());
		}
		return locations;
	}
	
	/**
	 * VALIDATORS
	 */
	/**
	 * Floor needs to be in range [0,maxFloor]
	 * @param floor
	 */
	private void validateFloor(int floor) {
		if(floor<0 || floor > this.maxFloor)
			throw new IllegalArgumentException("Floor needs to be in range [" + 0 + ", " + this.maxFloor + "]");
	}
	/**
	 * ElevatorId needs to be in range [0, amount_of_elevators]
	 * @param elevatorId
	 */
	private void validateId(int elevatorId) {
		if(elevatorId < 0 || elevatorId > elevators.size())
			throw new IllegalArgumentException("Elevator id needs to be between 0 and " + (elevators.size()-1));
		
	}
	/**
	 * 
	 * @param floor
	 * @param up
	 */
	private void validateFirstOrLast(int floor, boolean up) {
		if(up) {
			if(floor >= maxFloor)
				throw new IllegalArgumentException("You can't go up from last floor");
		}else {
			// going down
			if(floor <=0)
				throw new IllegalArgumentException("You can't go down from first floor");
		}
		
	}
	
	@Override
	public ElevatorState getState(int elevatorId) {
		return elevators.get(elevatorId).getState();
	}


	
	
	
	
}
