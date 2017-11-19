import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Engine {

	private UserInterface ui;
	private Scanner readTextInfo;
	private PriorityQueue queue;
	private Scanner input = new Scanner(System.in);
	private Scanner readFile;
	private Digraph graph;
	private ArrayList<City> cityNodeList;
	private ArrayList<Road> roadNodeList;
	Digraph testGraph;
	
	
	private int[][] test = new int[][] {{ 0, 66, 390, 0, 0 },
										{ 732, 0, 17, 0, 0 },
										{ 0, 0, 0, 273, 0 },
										{ 212, 0, 0, 0, 122 },
										{ 0, 0, 0, 29, 0 },
														};
		
	
	public Engine(UserInterface ui){
		this.ui = ui;
		queue = new PriorityQueue(9);
		cityNodeList = new ArrayList<>();
		roadNodeList = new ArrayList<>();
	}
	
	public void start() throws IOException{
		
		scanCity();
		//test(getCityNodes());
		scanRoad();
		//printArray(getRoadNodes());
		createGraph(getRoadNodes());
		//printGraph(graph);
		//createGraphTest();
		//System.out.println(testGraph.getGraphMatrix()[1][0]);
		printGraph(graph);
		test(getCityNodes());
		Dijkstra dijkstra = new Dijkstra(getCityNodes(), graph, 17);
		printArray(dijkstra.dikstra());
		
		
		
	}
	public void createGraphTest() {
		testGraph = new Digraph(5, getCityNodes(), test);
	}
	public void test(City[] cityNodes) {
		
		for(int i = 1; i < cityNodes.length; i++)
			System.out.print(cityNodes[i].getCityNumber() + " ");
		System.out.println();
	}
	public void test(Road[] roadNodes) {
	
		for(int i = 0; i < roadNodes.length; i++)
			System.out.println(roadNodes[i]);
		
	}
	
	public void printArray(int[] distance) {
		for(int i = 1; i < distance.length; i++)
			System.out.print(distance[i] + " ");
	}
	
	
	public String []getData(){
		String[] stringData = new String[5];
		
		return stringData;
	}
	
	public void createGraph(Road[] roadNodes) throws FileNotFoundException {
		scanCity();
		graph = new Digraph(getCityNodes().length, getCityNodes());
		//scanRoad();
		
		for(int i = 1; i < roadNodes.length; i++) {
			//System.out.print(roadNodes[i] + " ");
			graph.addEdge(roadNodes[i].from.getCityNumber(), roadNodes[i].to.getCityNumber(), roadNodes[i].getWeight());
		}
	}
	public void printGraph(Digraph graph) {
		System.out.println("The adjacency matrix for the given graph is: ");
        System.out.print("  ");
        for (int i = 1; i <= graph.vertices; i++)
            System.out.print(i + " ");
        System.out.println();

        for (int i = 1; i <= graph.vertices; i++) 
        {
            System.out.print(i + " ");
            for (int j = 1; j <= graph.vertices; j++) 
                System.out.print(graph.getEdge(i, j) + " ");
            System.out.println();
        }
        System.out.println();
    }
	
	public void printHeap(int[] heap, int lastIndex){
		
		//for (int i = 0; i < (lastIndex + 1); i++)
			//System.out.print(heap[i] + " ");
		
		for (int i = 0; i < heap.length;i++ ){
				if(heap[i] != 0)
					System.out.print(heap[i] + " ");
		}
			
		
	}
	
	
	public void scanCity() throws FileNotFoundException {
		try {
			readFile = new Scanner(new File("city(2).dat"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		if(cityNodeList.size() != 0)
			cityNodeList = new ArrayList<>();
		
		while(readFile.hasNext()) {
			int cityNumber = Integer.parseInt(readFile.next()); 
			String cityCode = readFile.next();
			String fullCityName = null;
			//if(cityNumber.equals("4|5|6|7|11|12|13|14|16|17")) {
			if(cityNumber == 4 || cityNumber == 5 || cityNumber == 6 || 
			   cityNumber == 7 || cityNumber == 9 || cityNumber == 11|| 
			   cityNumber == 12|| cityNumber == 13|| cityNumber == 14|| 
			   cityNumber == 16|| cityNumber == 17) {
				fullCityName = readFile.next() + " " + readFile.next();
			}
			else
				fullCityName = readFile.next();
			
			
			int population = Integer.parseInt(readFile.next());
			int elevation = Integer.parseInt(readFile.next());
			
			cityNodeList.add(new City(cityNumber, cityCode, fullCityName, population, elevation));
		}
		
	}
	
	public void scanRoad() {
		try {
			readFile = new Scanner(new File("road(2).dat"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		while(readFile.hasNext()) {
			int from = Integer.parseInt(readFile.next());
			int to = Integer.parseInt(readFile.next());
			int distance = Integer.parseInt(readFile.next());
		
		
		roadNodeList.add(new Road(getCityNodes()[from], getCityNodes()[to], distance));
		}
	}
	
	public City[] getCityNodes() {
		City[] cityNodes = new City[cityNodeList.size()+1];
		
		for(int i = 1; i < cityNodes.length; i++)
			cityNodes[i] = cityNodeList.get(i-1);
		
		return cityNodes;
		
	}
	public Road[] getRoadNodes() {
		Road[] roadNodes = new Road[roadNodeList.size()+1];
		
		for(int i = 1; i < roadNodes.length; i++)
			roadNodes[i] = roadNodeList.get(i-1);
		
		return roadNodes;
		
	}
	
	
	
}