package elevator;
/**
 * Known implementation: {@link Elevator}
 *
 */
public interface IElevator {
	public void addTarget(int floor);
	public void step();
	public int getOccupation();
	public int getPresentFloor();
}
