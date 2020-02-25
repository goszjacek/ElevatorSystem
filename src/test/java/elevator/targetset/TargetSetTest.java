package elevator.targetset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TargetSetTest {
	TargetsSet set = new TargetsSet(Arrays.asList(1,2,3,4,5));
	
	@Test
	void firstUpperTest() {		
		assertEquals(4, set.firstUpper(3));
	}
	
	@Test
	void anyUpperTest() {
		assertTrue(set.anyUpper(4));
	}
	@Test
	void anyLowerTest() {
		assertTrue(set.anyLower(2));
	}

}
