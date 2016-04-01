package bridgeandtorch.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Tree {

	public PriorityQueue<Node> openlist;
	public ArrayList<Node> closedlist;
	public ArrayList<Edge> Edges;

	private Node root;

	public Tree(Node root) {
		this.root = root;

		openlist = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.getF() < o2.getF())
					return -1;
				if (o1.getF() > o2.getF())
					return 1;

				return 0;
			}
		});
		openlist.add(this.root);
		closedlist = new ArrayList<Node>();
		Edges = new ArrayList<Edge>();
	}

	public Path getPath(Node lastNode) {
		Path path = new Path();

		Edge parent;
		Node current = lastNode;

		while ((parent = current.parentlink) != null) {
			path.AddEdge(parent);
			current = parent.start;
		}

		return path;
	}

	public void CreateNeighbors(Node current) {
		ArrayList<Move> moves = new ArrayList<Move>();

		Direction direction = current.state.torchatsource ? Direction.SourceToTarget
				: Direction.TargetToSource;

		ArrayList<Person> people = current.state.torchatsource ? current.state.sourcelist
				: current.state.targetlist;

		CreateMove(moves, direction, people);

		for (Move move : moves) {
			DoStep(current, move);
		}
	}

	public void CreateMove(ArrayList<Move> moves, Direction direction,
			ArrayList<Person> people) {
		// Bütün olasý yollarý oluþturur.
		for (int i = 0; i < people.size(); i++) {
			ArrayList<Person> combinationpeople = new ArrayList<Person>();
			combinationpeople.add(people.get(i));
			moves.add(new Move(direction, combinationpeople));
			for (int j = i + 1; j < people.size() && i < people.size(); j++) {
				ArrayList<Person> combinationpeople2 = new ArrayList<Person>();
				combinationpeople2.add(people.get(i));
				combinationpeople2.add(people.get(j));
				moves.add(new Move(direction, combinationpeople2));
			}
		}

	}

	public void DoStep(Node current, Move move) {
		// Þuanki nodedan belirtilen yolda ilerler ve zaten o node varsa
		// oluþturmaz yoksa yeni node oluþturulur.
		NodeState targetState = current.state.getNextState(move);

		// Kapalý listedeki nodelar pas geçilir.
		Node closedNode = closedlist.stream()
				.filter(o -> o.state.equals(targetState)).findFirst()
				.orElse(null);

		if (closedNode == null) {
			// Açýk listede node yoksa F,G,H deðerleri hesaplanýp node
			// oluþturulur ve açýk listeye eklenir.
			Node targetNode = openlist.stream()
					.filter(o -> o.state.equals(targetState)).findFirst()
					.orElse(null);

			if (targetNode == null) {
				targetNode = new Node();
				targetNode.state = targetState;
				targetNode.setG(current.getG() + move.getMaxCost());
				targetNode.setH(Algorithm.heuristic(targetNode));

				Edge newedge = new Edge(current, targetNode, move);
				Edges.add(newedge);

				targetNode.parentlink = newedge;
				openlist.add(targetNode);
			} else {
				// Node varsa edge olmayabilir kontrolü yapýlýr.
				Node temp = targetNode;
				Edge edge = Edges.stream().filter(e -> e.start == current)
						.filter(e -> e.end == temp)
						.filter(e -> e.move.equals(move)).findFirst()
						.orElse(null);

				if (edge == null) {
					edge = new Edge(current, temp, move);
					Edges.add(edge);
				}
			}
		}

	}
}
