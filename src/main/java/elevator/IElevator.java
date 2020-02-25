package elevator;
/**
 * Known implementation: {@link Elevator}
 *
 */
public interface IElevator {
	public void step();
	public int getOccupation();
	public int getPresentFloor();
	public void clean();
	public void addToIns(int floor);
	public ElevatorState getState();
	public void addToUps(int floor);
	public void addToDowns(int floor);
	public void setState(ElevatorState state);
}
