package bridgeandtorch.main;

import java.util.ArrayList;

public class Path {

	public ArrayList<Edge> edges;

	public Path() {
		edges = new ArrayList<Edge>();
	}

	public void AddEdge(Edge edge) {
		edges.add(0, edge);
	}

	public int getTotalCost() {
		return edges.stream().mapToInt(e -> e.move.getMaxCost()).sum();
	}

}
