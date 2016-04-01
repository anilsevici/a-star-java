package bridgeandtorch.main;

import java.util.concurrent.atomic.AtomicInteger;

public class Edge {

	public Node start;
	public Node end;
	public Move move;

	private static final AtomicInteger count = new AtomicInteger(-1);
	private int edgenumber;

	public Edge(Node start, Node end, Move move) {
		this.start = start;
		this.end = end;
		this.move = move;
		edgenumber = count.incrementAndGet();
	}

	public int getEdgenumber() {
		return edgenumber;
	}

}
