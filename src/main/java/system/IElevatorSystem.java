package system;

public interface IElevatorSystem {
	public void pickUp(int floor);
	public void step(); 
	public int getPresentFloor();
}
