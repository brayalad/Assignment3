import java.util.ArrayList;
public class Digraph {

	 int vertices;
	 int[][] graphMatrix;
	 CityNode[] cityNodes;
	 RoadNode[] roadNodes;
	 int[] distance;
	 int[] previous;
	 ArrayList<Integer> visted;
	 PriorityQueue queue;
	 
	
	public Digraph(int vertices, CityNode[] cityNodes) {
		this.vertices = vertices;
		graphMatrix = new int[vertices + 1][vertices + 1];
		distance = new int[vertices];
		previous = new int[vertices];
		visted = new ArrayList<>
		this.cityNodes = cityNodes;
	}
	
	public void addEdge(int from, int to, int edge) {
		
		graphMatrix[from][to] = edge;
		
	}
	
	public int getEdge(int from, int to) {
	
		return graphMatrix[from][to];
		
	}
	
	public int[] getNeighbor(int source) {
		
		ArrayList<Integer> neighborList = new ArrayList<>();
		
		for(int i = 0; i < neighborList.size(); i ++) 
			if(graphMatrix[source][i] != 0)
				neighborList.add( graphMatrix[source][i]);
		
		
		
		return listConversion(neighborList);
	}
	
	public int[] listConversion(ArrayList<Integer> neighborList) {
		int[] neighbors = new int[neighborList.size()];
		
		for(int i = 0; i < cityNodes.length; i++)
			neighbors[i] = neighborList.get(i);
		
		return neighbors;
		
	}
	
	public void dikstra(Digraph graph, int source) {
		distance[source] = 0;
		
		queue = new PriorityQueue(vertices);
		
		
		for(int v = 0; v < vertices; v++) {
			distance[v] = Integer.MAX_VALUE;
			previous[v] = 0;
			
			
			
		}
		
		queue.add(cityNodes[source], distance[source]);
		
		while(!queue.isEmpty()) {
			int u = queue.removeMin().city.cityNumber;
			
			
			
			
		}
	    private void evaluateNeighbours(int evaluationNode)
	    {
	        int edgeDistance = -1;
	        int newDistance = -1;
	 
	        for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++)
	        {
	            if (!settled.contains(destinationNode))
	            {
	                if (adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE)
	                {
	                    edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
	                    newDistance = distances[evaluationNode] + edgeDistance;
	                    if (newDistance < distances[destinationNode])
	                    {
	                        distances[destinationNode] = newDistance;
	                    }
	                    priorityQueue.add(new Node(destinationNode,distances[destinationNode]));
	                }   
	            }
	        }
	    }
		
		
	}
	
}


