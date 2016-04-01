package bridgeandtorch.main;

import java.util.concurrent.atomic.AtomicInteger;

public class Node {

	private int G;
	private int H;

	public NodeState state;
	public Edge parentlink = null;

	private static final AtomicInteger count = new AtomicInteger(-1);
	private int nodenumber;

	public Node() {
		nodenumber = count.incrementAndGet();
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public int getNodenumber() {
		return nodenumber;
	}

	public void setNodenumber(int nodenumber) {
		this.nodenumber = nodenumber;
	}

	public int getF() {
		return getG() + getH();
	}

}
