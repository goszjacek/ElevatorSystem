package helper;

import java.util.SortedSet;

public class Helper {
	public static Integer firstUpper(SortedSet<Integer> set, Integer val) {
		SortedSet<Integer> uppers = set.tailSet(val+1);
		if(uppers.isEmpty())
			return null;
		else return uppers.first();
	}
}
