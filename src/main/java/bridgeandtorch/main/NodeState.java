package bridgeandtorch.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NodeState {

	public ArrayList<Person> sourcelist;
	public ArrayList<Person> targetlist;
	public boolean torchatsource;

	public NodeState(boolean torchatSource, ArrayList<Person> source,
			ArrayList<Person> target) {
		this.sourcelist = source;
		this.targetlist = target;
		this.torchatsource = torchatSource;
	}

	public NodeState getNextState(Move move) {
		ArrayList<Person> tempsource = new ArrayList<Person>();
		tempsource.addAll(sourcelist);
		ArrayList<Person> temptarget = new ArrayList<Person>();
		temptarget.addAll(targetlist);

		for (Person person : move.getPeople()) {
			if (move.getDirection() == Direction.SourceToTarget) {
				tempsource.remove(person);
				temptarget.add(person);
			} else {
				tempsource.add(person);
				temptarget.remove(person);
			}
		}

		return new NodeState(!torchatsource, tempsource, temptarget);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeState other = (NodeState) obj;
		if (sourcelist == null) {
			if (other.sourcelist != null)
				return false;
		}
		if (targetlist == null) {
			if (other.targetlist != null)
				return false;
		}

		boolean result = exact(sourcelist, other.sourcelist)
				&& exact(targetlist, other.targetlist);

		if (!result)
			return false;

		if (torchatsource != other.torchatsource)
			return false;
		return true;
	}

	public boolean exact(ArrayList<Person> list1, ArrayList<Person> list2) {
		Set<Object> set1 = new HashSet<Object>();
		set1.addAll(list1);
		Set<Object> set2 = new HashSet<Object>();
		set2.addAll(list2);

		return set1.equals(set2);
	}

}
