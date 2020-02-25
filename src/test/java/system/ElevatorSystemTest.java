package system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ElevatorSystemTest {
	@AfterEach
	void setUp() {
		System.out.println("---------------------------------------");
	}
	
	
	/**
	 * Tests with one elevator
	 */	
	@Test
	void testWrongConstructorArguments() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			IElevatorSystem es = new ElevatorSystem(0,0);
		});
	}
	
	@Test
	void testWrongFloor() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			IElevatorSystem es = new ElevatorSystem(2);
			es.outerPickUp(3);
		});
	}
	
	@Test
	void testMovement() {
		IElevatorSystem es = new ElevatorSystem(2);
		es.step();
		es.outerPickUp(2);
		es.step();
		es.step();
		assertEquals(2, es.getPresentFloor(0));
		es.outerPickUp(1);
		es.step();
		es.step();
		assertEquals(1, es.getPresentFloor(0));
		es.outerPickUp(2);
		es.outerPickUp(0);
		es.step();
		assertEquals(2, es.getPresentFloor(0));
		es.step();
		es.step();
		assertEquals(0, es.getPresentFloor(0));
		es.step();
		assertEquals(0, es.getPresentFloor(0));
	}

	/**
	 * Tests with many elevators
	 */
	
	@Test
	void testManyElevators() {
		IElevatorSystem es = new ElevatorSystem(3, 2);
		es.outerPickUp(1);
		es.outerPickUp(3);
		es.step();
		assertEquals(1, es.getPresentFloor(0));
		assertEquals(1, es.getPresentFloor(1));
		es.step();
		es.step();
		assertEquals(1, es.getPresentFloor(0));
		assertEquals(3, es.getPresentFloor(1));
	}
	
	@Test
	void testWithInnerPickUp() {
		IElevatorSystem es = new ElevatorSystem(3, 2);
		es.outerPickUp(1);
		es.step();
		es.innerPickUp(0,3);
		es.step();
		es.step();
		assertEquals(es.getPresentFloor(0), 3);
		
	}
	
	@Test
	void testWithInnerPickUp2() {
		IElevatorSystem es = new ElevatorSystem(3, 2);
		es.outerPickUp(2);
		es.outerPickUp(3);
		es.step();
		es.innerPickUp(0, 1);
		es.innerPickUp(1, 2);
		es.step();
		es.step();
		assertEquals(es.getPresentFloor(0), 1);
		assertEquals(es.getPresentFloor(1), 3);
		es.step();
		assertEquals(es.getPresentFloor(1), 2);
	}
	
}
