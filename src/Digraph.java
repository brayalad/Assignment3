/**
 * This is the Digraph class. This class holds and represents an adjacency matrix
 * that represents a weighted directed graph that would hold the cities and the are
 * passed through by the engine class. This class has adding and removing edge 
 * functionalities
 * @author blayala
 *
 */
public class Digraph {

	/**
	 * Number of cities/vertices
	 */
	 private int vertices;
	 /**
	  * This a two-dimensional array that is an adjacency matrix that represents the graph, vertices, and weight of edges
	  */
	 private int[][] graphMatrix;
	 
	 /**
	  * This is the constructor that instantiates all the fields with their respective values
	  * @param vertices the number of cities/vertices
	  * @param cityNodes array of cities available
	  */
	 public Digraph(int vertices, City[] cityNodes) {
		this.vertices = vertices;
		graphMatrix = new int[vertices + 1][vertices + 1];
		
	 }
	
	/**
	 * Adds an edge/road to the adjacency matrix
	 * @param from city number of departure city
	 * @param to city number of destination city
	 * @param edge weight/distance of edge/road added
	 */
	 public void addEdge(int from, int to, int edge) {
		
		graphMatrix[from][to] = edge;
		
	 }
	
	 /**
	  * Removes an edge/road to the adjacency matrix
	  * @param from city number of departure city
	  * @param to city number of destination city
	  */
	 public void removeEdge(int from, int to) {
		
		graphMatrix[from][to] = 0;
		
	 }
	
	 /**
	  * Gets an edge/road to the adjacency matrix
	  * @param from city number of departure city
	  * @param to city number of destination city
	  */
	 public int getEdge(int from, int to) {
	
		return graphMatrix[from][to];
		
	 }
	
	 /**
	  * returns the adjacency matrix that represents the graph
	  * @return the graph matix
	  */
	 public int[][] getGraphMatrix(){
		return graphMatrix;
	 }
	
	 /**
	  * gets the number of cities/vertices
	  * @return number of vertices
	  */
	 public int getVertices(){
		return vertices;
	 }
	
	
}


