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
	private ArrayList<CityNode> cityNodeList;
	private ArrayList<RoadNode> roadNodeList;
	
	public Engine(UserInterface ui){
		this.ui = ui;
		queue = new PriorityQueue(9);
		cityNodeList = new ArrayList<>();
		roadNodeList = new ArrayList<>();
	}
	
	public void start() throws IOException{
		
		scanCity();
		test(getCityNodes());
		scanRoad();
		//test(getRoadNodes());
		
		createGraph(getRoadNodes());
		printGraph(graph);
		
		
	}
	
	public void test(CityNode[] cityNodes) {
		
		for(int i = 0; i < cityNodes.length; i++)
			System.out.println(cityNodes[i]);
		
	}
	public void test(RoadNode[] roadNodes) {
	
		for(int i = 0; i < roadNodes.length; i++)
			System.out.println(roadNodes[i]);
		
	}
	
	public String []getData(){
		String[] stringData = new String[5];
		
		return stringData;
	}
	
	public void createGraph(RoadNode[] roadNodes) throws FileNotFoundException {
		//scanCity();
		graph = new Digraph(getCityNodes().length);
		//scanRoad();
		
		for(int i = 0; i < roadNodes.length; i++)
			graph.addEdge(roadNodes[i].from, roadNodes[i].to, roadNodes[i].edge);
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
			
			cityNodeList.add(new CityNode(cityNumber, cityCode, fullCityName, population, elevation));
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
		
		
		roadNodeList.add(new RoadNode(from, to, distance));
		}
	}
	
	public CityNode[] getCityNodes() {
		CityNode[] cityNodes = new CityNode[cityNodeList.size()];
		
		for(int i = 0; i < cityNodes.length; i++)
			cityNodes[i] = cityNodeList.get(i);
		
		return cityNodes;
		
	}
	public RoadNode[] getRoadNodes() {
		RoadNode[] roadNodes = new RoadNode[roadNodeList.size()];
		
		for(int i = 0; i < roadNodes.length; i++)
			roadNodes[i] = roadNodeList.get(i);
		
		return roadNodes;
		
	}
	
	
	
}



