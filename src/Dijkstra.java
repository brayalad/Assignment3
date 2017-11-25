import java.util.ArrayList;

/**
 * This is the Dijkstra class. This class is responsible for containing and running
 * the Dijkstra algorithm, which is a shortest path algorithm that calculates the shortest
 * path from a source to all other vertices within the same graph.
 * @author blayala
 *
 */
public class Dijkstra {

	/**
	 * This is the city number of the source city
	 */
	private int source;
	/**
	 * This is the array that contains the predececors/previous cities in the path to which ever city
	 */
	private int[] predececor;
	/**
	 * This is the array that contains the distance between cities in the shortest path to whatever city
	 */
	private int[] distance;
	/**
	 * This a two-dimensional array that is an adjacency matrix that represents the graph, vertices, and weight of edges
	 */
	private int [][] graphMatrix;
	/**
	 * This is the priority queue that is used to find the smallest distance while calculating the shortest path
	 */
	private PriorityQueue availableVertices;
	/**
	 * An array that holds the cities/vertices that the graph contains
	 */
	private City[] cityNodes;
	/**
	 * This is the city number of the destination that the user is looking for the shortest path to
	 */
	private int destination;
	/**
	 * This is an Array List that holds the shortest path. Array list is used because the path will
	 * vary depending on source and destination
	 */
	private ArrayList<City> path;
	
	/**
	 * This is the constructor to the Dijkstra class. This instantiates all the fields with their 
	 * respective values
	 * @param cityNodes an array that contains all the city/vertices in the graph
	 * @param graph the Directed graph represented by an adjacency matrix
	 * @param source city number of the source city
	 * @param destination city number of the destination city
	 */
	public Dijkstra(City[] cityNodes, Digraph graph, int source, int destination) {
		this.source = source;
		graphMatrix = graph.getGraphMatrix();
		distance = new int[graph.getGraphMatrix().length];
		availableVertices = new PriorityQueue(graph.getGraphMatrix().length);
		predececor = new int[graph.getGraphMatrix().length];
		this.cityNodes = cityNodes;
		this.destination = destination;
		path = new ArrayList<>();
		
	}
	
	/**
	 * This is the Dijkstra algorithm. This algorithm is a single source algorithm that finds the 
	 * shortest path between two cities/vertices
	 * @return an array of distances and each distance is the shortest path to that city number of its index
	 */
	public int[] dikstra() {
		
		//for every city/vertex in graph
		for(int v = 1; v < graphMatrix.length-1; v++) {
		
			if((v) == source)
				distance[v] = 0; //setting distance from source to 0
			else {
				distance[v] = Integer.MAX_VALUE; //set distance to other cities/vertices to Infinity
				predececor[v] = 0; //setting the previous city to 0
			}
			availableVertices.add(cityNodes[v], distance[v]);	 //adds the city to the priority queue.
		}
			
		//runs until the priority queue is empty
		while(!availableVertices.isEmpty()) {
			
			int u = availableVertices.removeMin().city.getCityNumber(); //city with smallest distance 
			
			//for all neighbors of city/vertex being checked
			for(int v = 1; v < graphMatrix.length-1; v++) {
				if(graphMatrix[u][v] != 0) {
				
					int alt = distance[u] + graphMatrix[u][v]; //Alternative distance
					
					/*
					 * Checks if alternate distance is less then the current distance
					 * if alternate distance is less, alternate is the new current distance
					 */
					if(alt < distance[v]) {
						
						distance[v] = alt;
						predececor[v] = u;
						//the remove and add methods below act as a decrease_priorty function that would be found in a priority queue
						availableVertices.remove(v);
						availableVertices.add(cityNodes[v], distance[v]);
					}
				}
			}
		}
	
		return distance;
		
	}
	
	/**
	 * This method calculates the shortest path from source to destination using an array list
	 * @return shortest path from source to destination
	 */
	public ArrayList<City> getPath(){
		
		ArrayList<City> backwardPath = new ArrayList<>(); //Original path is backwards (destination to source)
		int previous = predececor[destination];
		backwardPath.add(cityNodes[destination]);
		while(previous != source) {
			backwardPath.add(cityNodes[previous]);
			previous = predececor[previous];
		}
		
		backwardPath.add(cityNodes[source]);
		
		//transforms destination to source path to source to destination path
		for(int i = backwardPath.size() - 1; i >= 0; i--)
			path.add(backwardPath.get(i));
		
		return path;
	}
	
}
