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
		assertEquals(2, es.getPresentFloor());
		es.pickUp(1);
		es.step();
		es.step();
		assertEquals(1, es.getPresentFloor());
		es.pickUp(2);
		es.pickUp(0);
		es.step();
		assertEquals(2, es.getPresentFloor());
		es.step();
		es.step();
		assertEquals(0, es.getPresentFloor());
		es.step();
		assertEquals(0, es.getPresentFloor());
	}

	
}
