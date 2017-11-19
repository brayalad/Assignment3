import java.util.ArrayList;

public class DiagraphMatrix {

	 int vertices;
	 int[][] graphMatrix;
	 City[] cityNodes;
	 Road[] roadNodes;
	 int[] distance;
	 int[] previous;
	 ArrayList<Integer> visited;
	 PriorityQueue queue;
	 
	
	public DiagraphMatrix(int vertices, City[] cityNodes) {
		this.vertices = vertices;
		graphMatrix = new int[vertices + 1][vertices + 1];
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
	
	public int[] dikstra(int source) {
		distance[source] = 0;
		
		queue = new PriorityQueue(vertices);
		
		
		for(int v = 0; v < vertices; v++) {
			distance[v] = Integer.MAX_VALUE;
			previous[v] = 0;
			
			queue.add(cityNodes[source], distance[source]);
			
		}
		
		
		
		while(!queue.isEmpty()) {
			int u = queue.removeMin().city.getCityNumber();
			
			for(int v = 0; v < cityNodes.length; v++) {
				if(graphMatrix[source][v] != 0) {
					
					int alt = distance[u] + graphMatrix[source][v];
					if(alt < distance[v]) {
						distance[v] = alt;
						previous[v] = u;
					}
					
					
				}
				
			}
			
			
		}
		
		return distance;
	}
	
	
	public void evaluateNeighbours(int from)
    {
        int edgeDistance = -1;
        int newDistance = -1;
 
        for (int to = 0; to < vertices; to++)
        {
            if (!visited.contains(to))
            {
                if (graphMatrix[from][to] != Integer.MAX_VALUE)
                {
                    edgeDistance = graphMatrix[from][to];
                    newDistance = distance[from] + edgeDistance;
                    if (newDistance < distance[to])
                    {
                        distance[to] = newDistance;
                    }
                    queue.add(cityNodes[to],distance[to]);
                }   
            }
        }
    }
	
}
