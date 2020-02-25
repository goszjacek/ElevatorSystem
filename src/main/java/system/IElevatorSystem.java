package system;
/**
 * Known implementation: {@link ElevatorSystem}
 *
 */
public interface IElevatorSystem {
	/**
	 * Pick up from a floor. 
	 * TODO: Directions up and down. 
	 * @param floor
	 */
	public void outerPickUp(int floor);
	
	/**
	 * Pick up inside of the specific elevator. No directions.
	 * Note: In the real system it should be implemented inside Elevator class. For purposes of the simulation
	 * we let provide this information through elevators system. 
	 * @param floor
	 */
	public void innerPickUp(int elevatorId, int floor);
	
	/**
	 * Step of the simulation.
	 */
	public void step(); 
	
	@Deprecated public int getPresentFloor();
	
	/**
	 * Get present position of specific elevator. 
	 * @param elevatorId
	 * @return
	 */
	int getPresentFloor(int elevatorId);
}
