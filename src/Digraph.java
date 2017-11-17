
public class Digraph {

	 int vertices;
	private int[][] graphMatrix;
	
	public Digraph(int vertices) {
		this.vertices = vertices;
		graphMatrix = new int[vertices + 1][vertices + 1];
	}
	
	public void addEdge(int from, int to, int edge) {
		
		graphMatrix[from][to] = edge;
		
	}
	
	public int getEdge(int from, int to) {
	
		return graphMatrix[from][to];
		
	}
	
	
	
}

