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
		test(getRoadNodes());
		
		
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
	
	public void createGraph() throws FileNotFoundException {
		scanCity();
		graph = new Digraph(getCityNodes().length);
		
		
		
		
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
			String cityNumberString = readFile.next();
			String cityCode = readFile.next();
			String fullCityName = null;
			//if(cityNumber.equals("4|5|6|7|11|12|13|14|16|17")) {
			if(cityNumberString.equals("4") || cityNumberString.equals("5") || cityNumberString.equals("6") || cityNumberString.equals("7") ||
			   cityNumberString.equals("11") || cityNumberString.equals("9") ||  cityNumberString.equals("12") || cityNumberString.equals("13") ||
			   cityNumberString.equals("14") || cityNumberString.equals("16") || cityNumberString.equals("17")) {
				fullCityName = readFile.next() + " " + readFile.next();
			}
			else
				fullCityName = readFile.next();
			String populationString = readFile.next();
			String elevationString = readFile.next();
			
			int cityNumber = Integer.parseInt(cityNumberString);
			int population = Integer.parseInt(populationString);
			int elevation = Integer.parseInt(elevationString);
			
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



