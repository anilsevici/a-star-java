package bridgeandtorch.main;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Algorithm {

	private ArrayList<Person> people;

	public boolean isfinish(NodeState state) {
		return state.sourcelist.isEmpty()
				&& state.targetlist.size() == people.size()
				&& !state.torchatsource;
	}

	public static int heuristic(Node node) {
		NodeState state = node.state;
		return state.targetlist.stream().mapToInt(o -> o.getCost()).sum();
	}

	public Path Astar(ArrayList<Person> all) {
		this.people = all;

		// Root node oluþtur ve açýk listeye ekle.
		Node root = new Node();
		root.setG(0);
		root.state = new NodeState(true, all, new ArrayList<Person>());
		root.setH(heuristic(root));

		Tree tree = new Tree(root);

		// Açýk listede eleman kalmayýncýya kadar döngü çalýþýr eðer target node
		// ulaþmadan çýkýlýrsa çözüm yok demektir.
		while (!tree.openlist.isEmpty()) {
			Node current = tree.openlist.poll();

			if (isfinish(current.state))
				return tree.getPath(current);

			// Seçili node kapalý listeye eklenir.
			tree.closedlist.add(current);
			tree.CreateNeighbors(current);

			ArrayList<Edge> edges = tree.Edges
					.stream()
					.filter(p -> p.start == current)
					.collect(
							Collectors
									.toCollection(() -> new ArrayList<Edge>()));

			for (Edge edge : edges) {
				Node neighbor = edge.end;

				int tempG = current.getG() + edge.move.getMaxCost();
				int tempH = heuristic(neighbor);
				int tempF = tempG + tempH;

				// current node üzerinden neighbor node'a ulaþmak daha avantajlý
				// var olan node öncelik kuyruðu olan açýk listedeki yeri
				// güncellenir.
				if (tempF < neighbor.getF()) {
					tree.openlist.remove(neighbor);
					neighbor.setG(tempG);
					neighbor.parentlink = edge;
					tree.openlist.add(neighbor);
				}
			}
		}
		return null;
	}
}
