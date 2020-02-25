package helper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class HelperTest {

	@Test
	void test() {
		SortedSet set = new TreeSet(Arrays.asList(1,2,3,4,5));
		assertEquals(4, Helper.firstUpper(set, 3));
	}

}
