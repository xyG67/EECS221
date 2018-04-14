package eecs221.Util;

import java.util.ArrayList;

import eecs221.Model.Edge;
import eecs221.Model.Node;

public class Graph {
	private Node[] nodes;
	private int noOfNodes;
	private Edge[] edges;
	private int noOfEdges;

	public Graph(Edge[] edges) {
		this.edges = edges;

		this.noOfNodes = calculateNoOfNodes(edges);
		this.nodes = new Node[this.noOfNodes];
		for (int n = 0; n < this.noOfNodes; n++) {
			this.nodes[n] = new Node();
		}

		// add all the edges to the nodes, each edge added to two nodes (to and from)

		this.noOfEdges = edges.length;

		for (int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++) {
			this.nodes[edges[edgeToAdd].getStartNode()].getEdges().add(edges[edgeToAdd]);
			this.nodes[edges[edgeToAdd].getEndNode()].getEdges().add(edges[edgeToAdd]);
		}
	}
	
	//ei
	private int calculateNoOfNodes(Edge[] edges) {
		int noOfNodes = 0;
		for (Edge e : edges) {
			if (e.getEndNode() > noOfNodes)
				noOfNodes = e.getEndNode();
			
			if (e.getStartNode() > noOfNodes)
				noOfNodes = e.getStartNode();	
		}

		noOfNodes++;
		return noOfNodes;
	}

	public void calculateShortestDistances() {
		// node 0 as source
		this.nodes[0].setDistanceFromSource(0);
		int nextNode = 0;
		// visit every node
		for (int i = 0; i < this.nodes.length; i++) {
			
			// loop around the edges of current node
			ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
			
			for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
				
				int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);
				
				// only if not visited
				if (!this.nodes[neighbourIndex].isVisited()) {
					
					int tentative = this.nodes[nextNode].getDistanceFromSource()
							+ currentNodeEdges.get(joinedEdge).getLength();
					if (tentative < nodes[neighbourIndex].getDistanceFromSource()) {
						nodes[neighbourIndex].setDistanceFromSource(tentative);
					}
					
				}
			}
			// all neighbours checked so node visited
			nodes[nextNode].setVisited(true);
			// next node must be with shortest distance
			nextNode = getNodeShortestDistanced();
		}
	}

	private int getNodeShortestDistanced() {
		int storedNodeIndex = 0;
		int storedDist = Integer.MAX_VALUE;
		for (int i = 0; i < this.nodes.length; i++) {
			int currentDist = this.nodes[i].getDistanceFromSource();
			if (!this.nodes[i].isVisited() && currentDist < storedDist) {
				storedDist = currentDist;
				storedNodeIndex = i;
			}
		}
		return storedNodeIndex;
	}

	public void printResult() {
		String output = "Number of nodes = " + this.noOfNodes;
		output += "\nNumber of edges = " + this.noOfEdges;
		for (int i = 0; i < this.nodes.length; i++) {
			output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource());
		}
		System.out.println(output);
	}

	public Node[] getNodes() {
		return nodes;
	}

	public int getNoOfNodes() {
		return noOfNodes;
	}

	public Edge[] getEdges() {
		return edges;
	}

	public int getNoOfEdges() {
		return noOfEdges;
	}

}
