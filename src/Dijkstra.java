
public class Dijkstra {

	Digraph graph;
	int source;
	int[] predececor;
	int[] distance;
	int [][] graphMatrix;
	PriorityQueue availableVertices;
	City[] cityNodes;
	int[] visted;
	
	public Dijkstra(City[] cityNodes, Digraph graph, int source) {
		this.source = source;
		graphMatrix = graph.getGraphMatrix();
		distance = new int[graph.getGraphMatrix().length];
		availableVertices = new PriorityQueue(graph.getGraphMatrix().length);
		predececor = new int[graph.getGraphMatrix().length];
		this.cityNodes = cityNodes;
		//System.out.println(graph.getGraphMatrix().length);
	}
	
	public int[] dikstra() {
		
		for(int v = 1; v < graphMatrix.length-1; v++) {
		
			if((v) == source)
				distance[v] = 0;
			else {
				distance[v] = Integer.MAX_VALUE;
				predececor[v] = 0;
				
				availableVertices.add(cityNodes[v], distance[v]);
				
			}
		
			
			
			
		}
		//System.out.println(availableVertices.getLastIndex());
		
		printArray(availableVertices.getHeap(), availableVertices.getLastIndex());
			
		
		
		
		while(!availableVertices.isEmpty()) {
			
			int u = availableVertices.removeMin().city.getCityNumber();
			
			for(int v = 1; v < graphMatrix.length-1; v++) {
				if(graphMatrix[u][v] != 0) {
					
					int alt = distance[u] + graphMatrix[u][v];
					if(alt < distance[v]) {
						
						distance[v] = alt;
						predececor[v] = u;
						
						//System.out.print(availableVertices.searchRemoveIndex(v+1) + " ");
						availableVertices.remove(v);
						availableVertices.add(cityNodes[v], distance[v]);
					}
					
				}
				//printArray(availableVertices.getHeap(), availableVertices.getLastIndex());
				
			}
			
		}
		
		
		return distance;
		
		
	}
	
	public void printArray(QueueNode[] heap,int lastIndex) {
		for(int i = 1; i <= lastIndex; i++ )
			System.out.println(heap[i]);
		System.out.println();
	}
		
}
