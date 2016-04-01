package bridgeandtorch.test;

import java.util.ArrayList;
import bridgeandtorch.main.Algorithm;
import bridgeandtorch.main.Edge;
import bridgeandtorch.main.Path;
import bridgeandtorch.main.Person;

public class Test {
	public static void main(String[] args) {

		ArrayList<Person> people = null;

		Person a = new Person("A", 1);
		Person b = new Person("B", 2);
		Person c = new Person("C", 5);
		Person d = new Person("D", 8);
		people = new ArrayList<Person>();
		people.add(a);
		people.add(b);
		people.add(c);
		people.add(d);

		Algorithm astar = new Algorithm();
		Path path = astar.Astar(people);

		for (Edge edge : path.edges) {
			System.out.print(edge.move.getDirection() + "  People="
					+ edge.move.getPeople() + "  Cost="
					+ edge.move.getMaxCost());
			System.out.println();
		}

		System.out.println();
		System.out.println("Total Cost=" + path.getTotalCost());

	}
}
