
public class Digraph {

	private int vertices;
	private int[][] graphMatrix;
	
	public Digraph(int vertices) {
		this.vertices = vertices;
		graphMatrix = new int[vertices + 1][vertices + 1];
	}
	
	public void addEdge(int to, int from, int edge) {
		
		graphMatrix[to][from] = edge;
		
	}
	
	
	
	
	
}

