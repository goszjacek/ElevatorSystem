package elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import elevator.targetset.TargetsSet;

class ElevatorTest {
	
	
	@Test
	void test() {
		Elevator elevator = new Elevator(2);
		assertEquals(3,elevator.getFloorsAmount());
		assertEquals("|WW|  |  |", elevator.toString());
	}
	
	@Test
	void testComparable() throws Exception{
		Elevator e1 = new Elevator(1);
		Field insField = e1.getClass().getDeclaredField("ins");
		insField.setAccessible(true);
		insField.set(e1, new TargetsSet(Arrays.asList(1)));
		Elevator e2 = new Elevator(1);
		List<Elevator> els = Arrays.asList(e1,e2);
		List<Elevator> sorted = els.stream().sorted().collect(Collectors.toList());
		assertTrue(sorted.get(1) == e1);
		
	}

}
