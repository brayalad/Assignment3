import java.util.ArrayList;
public class Digraph {

	 int vertices;
	 int[][] graphMatrix;
	 City[] cityNodes;
	 Road[] roadNodes;
	 int[] distance;
	 int[] previous;
	 ArrayList<Integer> visited;
	 PriorityQueue queue;
	 
	
	public Digraph(int vertices, City[] cityNodes) {
		this.vertices = vertices;
		graphMatrix = new int[vertices + 1][vertices + 1];
		distance = new int[vertices];
		previous = new int[vertices];
		visited = new ArrayList<>();
		this.cityNodes = cityNodes;
	}
	public Digraph(int vertices, City[] cityNodes, int[][] test) {
		this.vertices = vertices;
		graphMatrix = test;
		distance = new int[vertices];
		previous = new int[vertices];
		visited = new ArrayList<>();
		this.cityNodes = cityNodes;
	}
	
	public void addEdge(int from, int to, int edge) {
		
		graphMatrix[from][to] = edge;
		
	}
	
	public int getEdge(int from, int to) {
	
		return graphMatrix[from][to];
		
	}
	
	public int[][] getGraphMatrix(){
		return graphMatrix;
	}
	
	
	
}


