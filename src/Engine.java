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
	private Dijkstra dijkstra;
	
	
	
		
	
	public Engine(UserInterface ui){
		this.ui = ui;
		cityNodeList = new ArrayList<>();
		roadNodeList = new ArrayList<>();
	}
	
	public void run(){
		
		ui.menu(1);
		ui.menu(2);
		boolean run = true;
		while(run) {
		String[] input = ui.getInput();
		
		if(input.length == 1){
			
			if(input[0].equalsIgnoreCase("Q")){
				ui.menu(3);
				query();
			}
			else if(input[0].equalsIgnoreCase("D")){
				ui.menu(4);
				shortestPath();
			}
			else if(input[0].equalsIgnoreCase("R")){
				
			}
			else if(input[0].equalsIgnoreCase("R")){


			}
			else if(input[0].equalsIgnoreCase("H")){
				ui.menu(1);
			}
			else if(input[0].equalsIgnoreCase("E")){
				
			}
			else
				ui.error();
		}
		else
			System.out.println("Only one comand");
		
		
		}
	}
	
		
		
	
	public void start() throws IOException{
		
		scanCity();
		scanRoad();
		createGraph(getRoadNodes());
		run();
		
	
		//printGraph(graph);
		//test(getCityNodes());
		//Dijkstra dijkstra = new Dijkstra(getCityNodes(), graph, 3);
		//printArray(dijkstra.dikstra());
		
		
		
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
		
		
		for(int i = 1; i < roadNodes.length; i++) {
			
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
	
	
	public void shortestPath() {
		
		String[] searchCityCodes = ui.getInput();
		
		if(searchCityCodes.length == 2) {
			
			City from = cityExist(searchCityCodes[0]);
			City to = cityExist(searchCityCodes[1]);
			
			if(from != null && to != null) {
				
				dijkstra = new Dijkstra(getCityNodes(), graph, from.getCityNumber(), to.getCityNumber());
				System.out.println(dijkstra.dikstra()[to.getCityNumber()]);
				
				printArrayList(dijkstra.getPath());
			}
			else 
				ui.error();
			
			
			
		}
		else
			System.out.println("Only 1 command");
		
	}
	
	
	public void printArrayList(ArrayList<City> path) {
		
		for(int i = 0; i < path.size(); i ++)
			if(i == 0)
				System.out.print(path.get(i).getCityName());
			else
				System.out.print( ", " + path.get(i).getCityName());
	}
	
	
	
	
	
	
	
	
	
	
	public void query(){
		
		String[] searchCityCode = ui.getInput();
		
		
		if(searchCityCode.length == 1) {
		City searchingCity = cityExist(searchCityCode[0]);
		if(searchingCity != null)
			System.out.println(searchingCity);
		else
			ui.error();
		}
		else
			System.out.println("Only one command");
	}
	
	public City cityExist(String searchCityCode){
		
		City[] cities = getCityNodes();
		City searchCity = null;
		
		for(int i = 1; i < cities.length; i++)
			if(cities[i].getCityCode().equalsIgnoreCase(searchCityCode))
				searchCity = cities[i];
		
		
		
		
		return searchCity;
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
	
	
}