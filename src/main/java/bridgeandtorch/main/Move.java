package bridgeandtorch.main;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

public class Move {

	private ArrayList<Person> people;
	private Direction direction;

	public Move(Direction direction, ArrayList<Person> people) {
		this.direction = direction;
		this.people = people;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getMaxCost() {
		IntSummaryStatistics maxcost = people.stream().collect(
				Collectors.summarizingInt(c -> c.getCost()));

		return maxcost.getMax();

	}
}
