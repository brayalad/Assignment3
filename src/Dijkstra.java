
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
		System.out.println(graph.getGraphMatrix().length);
	}
	
	public int[] dikstra() {
		
		for(int v = 0; v < graphMatrix.length-1; v++) {
		
			if((v+1) == source)
				distance[v] = 0;
			else {
				distance[v] = Integer.MAX_VALUE;
				predececor[v] = 0;
				System.out.println("test1");
				availableVertices.add(cityNodes[v], distance[v]);
				System.out.println("test2");
			}
		
			
			
			
		}
		System.out.println(availableVertices.getLastIndex());
		
		printArray(availableVertices.getHeap());
			
		
		
		
		while(!availableVertices.isEmpty()) {
			
			int u = availableVertices.removeMin().city.getCityNumber() - 1;
			
			for(int v = 0; v < graphMatrix.length; v++) {
				if(graphMatrix[u][v] != 0) {
					
					int alt = distance[u] + graphMatrix[u][v];
					if(alt < distance[v]) {
						
						distance[v] = alt;
						predececor[v] = u+1;
						System.out.println(v);
						//System.out.println(availableVertices.searchRemoveIndex(v));
						availableVertices.remove(v);
						availableVertices.add(cityNodes[v], distance[v]);
					}
					
				}
				
				
			}
			
		}
		
		
		return distance;
		
		
	}
	
	public void printArray(QueueNode[] heap) {
		for(int i = 0; i < heap.length; i++ )
			System.out.println(heap[i]);
	}
}
