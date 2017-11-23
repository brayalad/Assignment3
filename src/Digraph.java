
public class Digraph {

	 private int vertices;
	 private int[][] graphMatrix;
	 
	 public Digraph(int vertices, City[] cityNodes) {
		this.vertices = vertices;
		graphMatrix = new int[vertices + 1][vertices + 1];
		
	}
	
	
	public void addEdge(int from, int to, int edge) {
		
		graphMatrix[from][to] = edge;
		
	}
	
	public void removeEdge(int from, int to) {
		
		graphMatrix[from][to] = 0;
		
	}
	
	public int getEdge(int from, int to) {
	
		return graphMatrix[from][to];
		
	}
	
	public int[][] getGraphMatrix(){
		return graphMatrix;
	}
	
	public int getVertices(){
		return vertices;
	}
	
	
}


