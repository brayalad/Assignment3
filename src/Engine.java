import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Engine {

	private UserInterface ui;
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
	
	public void start() throws IOException{
		
		scanCity();
		scanRoad();
		createGraph(getRoadNodes());
		run();
		
	}
	
	
	
	private void run(){
		
		
		boolean run = true;
		while(run) {
			
			ui.menu(1);
			ui.menu(2);
			@SuppressWarnings("unused")
			int integerTest = 0;
			String[] input = ui.getInput();
			
			if(input != null) {
				
				try {
					integerTest = Integer.parseInt(input[0]);
					ui.error(10);
					ui.error(9);
					
				} catch (NumberFormatException e) {
					if(input.length == 1){
						
						if(input[0].equalsIgnoreCase("Q")){
							ui.menu(3);
							query();
						}
						else if(input[0].equalsIgnoreCase("D")){
							ui.menu(4);
							shortestPath();
						}
						else if(input[0].equalsIgnoreCase("I")){
							ui.menu(5);
							insertRoad();
						}
						else if(input[0].equalsIgnoreCase("R")){
							ui.menu(4);
							removeRoad();
						}
						else if(input[0].equalsIgnoreCase("H")){
							ui.menu(1);
						}
						else if(input[0].equalsIgnoreCase("E")){
							System.exit(0);
						}
						else
							ui.error(1);
					}
					else {
						ui.error(3);
						ui.error(9);
					}
					
				}
		
				
		
			}
			else {
				ui.error(2);
				ui.error(9);
			}
		}
	}
	
		
		
	
	
	
	private void createGraph(Road[] roadNodes) throws FileNotFoundException {
		scanCity();
		graph = new Digraph(getCityNodes().length, getCityNodes());
		
		
		for(int i = 1; i < roadNodes.length; i++) {
			
			graph.addEdge(roadNodes[i].getFrom().getCityNumber(), roadNodes[i].getTo().getCityNumber(), roadNodes[i].getWeight());
		}
	}
	
	private void shortestPath() {
		
		@SuppressWarnings("unused")
		int integerTest = -1;
		String[] searchCityCodes = null; 
				
		searchCityCodes = ui.getInput();
		
		if(searchCityCodes != null) {
		
			try {
				integerTest = Integer.parseInt(searchCityCodes[0]);
				integerTest = Integer.parseInt(searchCityCodes[1]);
				ui.error(10);
				ui.error(9);
			} catch (NumberFormatException e1){
			
				if(searchCityCodes.length == 2 ) {
			
					City from = cityExist(searchCityCodes[0]);
					City to = cityExist(searchCityCodes[1]);
			
					if(from != null && to != null) {
				
						dijkstra = new Dijkstra(getCityNodes(), graph, from.getCityNumber(), to.getCityNumber());
				
				
						ui.printShortestPath(from.getCityName(), to.getCityName(), dijkstra.dikstra()[to.getCityNumber()], dijkstra.getPath());
					}else { 
						if(from == null)
						ui.error(5);
						if(to == null)
							ui.error(6);
					}
			
			
				}else {
					ui.error(3);
					ui.error(9);
				}
			}
		}else {
			ui.error(2);
			ui.error(9);
		}
	}
	
	
	private void query(){
		
		
		String[] searchCityCode = null;
		
		
			@SuppressWarnings("unused")
			int integerTest = -1;
			
			searchCityCode = ui.getInput();
			
			if(searchCityCode != null) {
				try {
					integerTest = Integer.parseInt(searchCityCode[0]);
					ui.error(10);
					ui.error(9);
				} catch (NumberFormatException e) {
					if(searchCityCode.length == 1) {
						City searchingCity = cityExist(searchCityCode[0]);
						if(searchingCity != null) {
							ui.printCity(searchingCity);
							
						}
						else {
							ui.error(4);
							ui.error(9);
						}	
					}
					else {
						ui.error(3);
						ui.error(9);
					}
				}
			}
			else {
				ui.error(11);
				ui.error(9);
			}
		
	
	}
	private City cityExist(String searchCityCode){
		
		City[] cities = getCityNodes();
		City searchCity = null;
		
		for(int i = 1; i < cities.length; i++)
			if(cities[i].getCityCode().equalsIgnoreCase(searchCityCode))
				searchCity = cities[i];
		
		
		
		
		return searchCity;
	}
	
	
	private void insertRoad(){
		
		@SuppressWarnings("unused")
		int integerTest = -1;
		
		String[] roadAdded = null;
		
		roadAdded = ui.getInput();
			
			if(roadAdded != null) {
			
				try {
					integerTest = Integer.parseInt(roadAdded[0]);
					integerTest = Integer.parseInt(roadAdded[1]);
					ui.error(10);
					ui.error(9);
				} catch (NumberFormatException e1) {
					
					if(roadAdded.length == 3) {
			
						City from = cityExist(roadAdded[0]);
						City to = cityExist(roadAdded[1]);
						int distance = Integer.MIN_VALUE;
			
						try {
							distance = Integer.parseInt(roadAdded[2]);
						} catch (NumberFormatException e) {
							ui.error(7);
						}
					
						if(from != null && to != null && distance != Integer.MIN_VALUE && distance > 0) {
				
				
				
							if(graph.getEdge(from.getCityNumber(), to.getCityNumber()) == 0) {
								graph.addEdge(from.getCityNumber(), to.getCityNumber(), distance);
								ui.printGraphManipulation(from.getCityName(), to.getCityName(), distance, 1);
							}else { 
								ui.error(1,from.getCityName(),to.getCityName());
								ui.error(9);
							}
						}else {
							if(from == null)
								ui.error(5);
							if(to == null)
								ui.error(6);
							if(distance < 0 && distance != Integer.MIN_VALUE)
								ui.error(8);
						}
			
			
					}else { 
						ui.error(3);
						ui.error(9);
					}
				}
			}else {
				ui.error(2);
				ui.error(9);
			}
		
	}
	
	private void removeRoad() {
		
		@SuppressWarnings("unused")
		int integerTest = -1;
		String[] roadAdded = null; 
		
		roadAdded = ui.getInput();
		
		
		if(roadAdded != null) {
		
			try {
				integerTest = Integer.parseInt(roadAdded[0]);
				integerTest = Integer.parseInt(roadAdded[1]);
				ui.error(10);
				ui.error(9);
			} catch (NumberFormatException e1) {
			
				if(roadAdded.length == 2) {
			
					City from = cityExist(roadAdded[0]);
					City to = cityExist(roadAdded[1]);
			
			
					if(from != null && to != null) {
				
				
				
						if(graph.getEdge(from.getCityNumber(), to.getCityNumber()) != 0) {
							graph.removeEdge(from.getCityNumber(), to.getCityNumber());
							ui.printGraphManipulation(from.getCityName(), to.getCityName(), 0, 2);
						}else {
							ui.error(1,from.getCityName(),to.getCityName());
							ui.error(9);
						}
					}else {
						if(from == null)
							ui.error(5);
						if(to == null)
							ui.error(6);
				
					}
			
			
				}else { 
					ui.error(3);
				}
			}
		}else {
			ui.error(2);
		}
		
	}
	
	
	private City[] getCityNodes() {
		City[] cityNodes = new City[cityNodeList.size()+1];
		
		for(int i = 1; i < cityNodes.length; i++)
			cityNodes[i] = cityNodeList.get(i-1);
		
		return cityNodes;
		
	}
	private Road[] getRoadNodes() {
		Road[] roadNodes = new Road[roadNodeList.size()+1];
		
		for(int i = 1; i < roadNodes.length; i++)
			roadNodes[i] = roadNodeList.get(i-1);
		
		return roadNodes;
		
	}
	
	
	
	private void scanCity() throws FileNotFoundException {
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
			
			if(cityNumber == 4 || cityNumber == 5 || cityNumber == 6 || 
			   cityNumber == 7 || cityNumber == 9 || cityNumber == 11|| 
			   cityNumber == 12|| cityNumber == 13|| cityNumber == 14|| 
			   cityNumber == 16|| cityNumber == 17) {
				fullCityName = readFile.next() + " " + readFile.next();
			}else
				fullCityName = readFile.next();
			
			
			int population = Integer.parseInt(readFile.next());
			int elevation = Integer.parseInt(readFile.next());
			
			cityNodeList.add(new City(cityNumber, cityCode, fullCityName, population, elevation));
		}
		
	}
	
	private void scanRoad() {
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