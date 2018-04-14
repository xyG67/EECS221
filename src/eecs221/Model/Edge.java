package eecs221.Model;

public class Edge {
	private int startNode;
	private int endNode;
	private int length;
	
	public Edge(int startNode, int endNode, int length) {
		super();
		this.startNode = startNode;
		this.endNode = endNode;
		this.length = length;
	}

	public int getStartNode() {
		return startNode;
	}

	public int getEndNode() {
		return endNode;
	}

	public int getLength() {
		return length;
	}
	
	public int getNeighbourIndex(int nodeIndex) {
		if(this.startNode == nodeIndex) {
			return this.endNode;
		}else {
			return this.startNode;
		}
	}
}
