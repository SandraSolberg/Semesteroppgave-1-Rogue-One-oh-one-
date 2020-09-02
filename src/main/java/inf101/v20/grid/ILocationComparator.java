package inf101.v20.grid;

import java.util.Comparator;

public class ILocationComparator implements Comparator<ILocation> {
	
	private ILocation loc;

	public ILocationComparator(ILocation loc) {
		this.loc = loc;
	}

	@Override
	public int compare(ILocation loc1, ILocation loc2) {
		int dist1 = loc.gridDistanceTo(loc1);
		int dist2 = loc.gridDistanceTo(loc2);
		return dist1 - dist2;
	}

}
