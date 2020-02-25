package elevator;
/**
 * Known implementation: {@link Elevator}
 *
 */
public interface IElevator {
	/**
	 * Simulates one step of the simulation.
	 */
	public void step();
	
	/**
	 * Calculate how much is the elevator occupied. 
	 * @return
	 */
	public int getOccupation();
	
	/**
	 * Where is the elevator located?
	 * @return
	 */
	public int getPresentFloor();
	
	/**
	 * Add a target from an innerPickUp.
	 * @param floor
	 */
	public void addToIns(int floor);
	
	/**
	 * What is the state of the elevator?
	 * @return
	 */
	public ElevatorState getState();
	
	/**
	 * Add an "up" target from an outerPickUp
	 * @param floor
	 */
	public void addToUps(int floor);
	
	/**
	 * Add an "down" target from an outerPickUp
	 * @param floor
	 */
	public void addToDowns(int floor);
	
	/**
	 * Set the state of the elevator
	 * @param state {@link ElevatorState}
	 */
	public void setState(ElevatorState state);
}
