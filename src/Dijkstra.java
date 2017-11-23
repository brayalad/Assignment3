import java.util.ArrayList;
public class Dijkstra {

	Digraph graph;
	int source;
	int[] predececor;
	int[] distance;
	int [][] graphMatrix;
	PriorityQueue availableVertices;
	City[] cityNodes;
	int[] visted;
	int destination;
	ArrayList<City> path;
	
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
	
	public int[] dikstra() {
		
		for(int v = 1; v < graphMatrix.length-1; v++) {
		
			if((v) == source)
				distance[v] = 0;
			else {
				distance[v] = Integer.MAX_VALUE;
				predececor[v] = 0;
				
				
				
			}
		
			availableVertices.add(cityNodes[v], distance[v]);
			
			
		}
		//System.out.println(availableVertices.getLastIndex());
		
		//printArray(availableVertices.getHeap(), availableVertices.getLastIndex());
			
		
		
		
		while(!availableVertices.isEmpty()) {
			
			int u = availableVertices.removeMin().city.getCityNumber();
			//System.out.println(u);
			for(int v = 1; v < graphMatrix.length-1; v++) {
				if(graphMatrix[u][v] != 0) {
					
					int alt = distance[u] + graphMatrix[u][v];
					//System.out.println(alt);
					if(alt < distance[v]) {
						
						distance[v] = alt;
						predececor[v] = u;
						
						//System.out.println(availableVertices.searchRemoveIndex(v) + " ");
						//System.out.println(v);
						availableVertices.remove(v);
						availableVertices.add(cityNodes[v], distance[v]);
					}
					
				}
				//printArray(availableVertices.getHeap(), availableVertices.getLastIndex());
				
			}
			
		}
		
		
		return distance;
		
		
	}
	
	public ArrayList<City> getPath(){
		
		ArrayList<City> backwardPath = new ArrayList<>();
		int previous = predececor[destination];
		backwardPath.add(cityNodes[destination]);
		while(previous != source) {
			backwardPath.add(cityNodes[previous]);
			previous = predececor[previous];
			
		}
		backwardPath.add(cityNodes[source]);
		
		for(int i = backwardPath.size() - 1; i >= 0; i--)
			path.add(backwardPath.get(i));
		
		
		return path;
	}
	
	
	public void printArray(QueueNode[] heap,int lastIndex) {
		for(int i = 1; i <= lastIndex; i++ )
			System.out.println(heap[i]);
		System.out.println();
	}
		public void printArray() {
			for(int i = 1; i < predececor.length; i++)
			System.out.print(predececor[i] + " ");
			System.out.println();
		}
		public void printArrayCity() {
			for(int i = 1; i < cityNodes.length; i++)
			System.out.print(cityNodes[i] + " ");
			System.out.println();
		}
}
