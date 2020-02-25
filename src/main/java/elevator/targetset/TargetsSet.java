package elevator.targetset;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class TargetsSet extends TreeSet<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * For tests only
	 * @param asList
	 */
	public TargetsSet(List<Integer> list) {
		super(list);
	}

	public TargetsSet() {
		super();
	}

	public Integer firstUpper(Integer val) {
		SortedSet<Integer> uppers = this.tailSet(val+1);
		if(uppers.isEmpty())
			return null;
		else return uppers.first();
	}
	
	public boolean anyUpper(Integer val) {
		SortedSet<Integer> uppers = this.tailSet(val+1);
		return !uppers.isEmpty();
	}
	
	public boolean anyLower(Integer val) {
		SortedSet<Integer> lowers = this.headSet(val);
		return !lowers.isEmpty();
	}
	
}
