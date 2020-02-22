package system;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ElevatorSystemTest {

	
	@Test
	void testWrongFloor() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			ElevatorSystem es = new ElevatorSystem(2);
			es.pickUp(3);
		});
	}
	
	@Test
	void testMovement() {
		ElevatorSystem es = new ElevatorSystem(2);
		es.step();
		es.pickUp(2);
		es.step();
		es.step();
		assertEquals(es.getPresentFloor(),2);
		es.pickUp(1);
		es.step();
		es.step();
		assertEquals(es.getPresentFloor(), 1);
		es.pickUp(2);
		es.pickUp(0);
		es.step();
		assertEquals(es.getPresentFloor(),2);
		es.step();
		es.step();
		assertEquals(es.getPresentFloor(), 0);
		es.step();
		assertEquals(es.getPresentFloor(), 0);
	}

}
