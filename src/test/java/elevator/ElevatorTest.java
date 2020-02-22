package elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ElevatorTest {
	
	
	@Test
	void test() {
		Elevator elevator = new Elevator(2);
		assertEquals(3,elevator.getFloorsAmount());
		assertEquals("|WW|  |  |", elevator.toString());
	}

}
