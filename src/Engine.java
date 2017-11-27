import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the Engine class. Everything that that this program does happens within this class.
 * This class receives input from the user through the UserInterface class then uses that data 
 * to execute program functions such as finding shortest distance and adding or removing roads.
 * This class is the connecting element between all other classes, no other classes interact directly 
 * with each other unless it is through the Engine class
 * @author blayala
 *
 */
public class Engine {

	/**
	 * This is the instance of the UserInterface class that is responsible for all of the interaction between the user 
	 * and the program and all its components.
	 */
	private UserInterface ui;
	/**
	 * This is the instance of the Scanner class. In the class, instead of taking in input from the user, it instead takes
	 * in input from the files. More specifically in this program, files that contain city and road information
	 */
	private Scanner readFile;
	/**
	 * This is an instance of the weighted directed graph that is holds the cities and roads in an adjacency matrix
	 */
	private Digraph graph;
	/**
	 * This is an array list that holds the city nodes while the file that contains cities is being read. Cities are 
	 * read from file provided and transfered into this array list. An array list was used so that this program will be
	 * usable with other files that contain a different number of cities.
	 */
	private ArrayList<City> cityNodeList;
	/**
	 * This is an array list that holds the road nodes while the file that contains roads is being read. Roads are 
	 * read from file provided and transfered into this array list. An array list was used so that this program will be
	 * usable with other files that contain a different number of roads.
	 */
	private ArrayList<Road> roadNodeList;
	/**
	 * This is the instance of the Dijkstra class. This class contains the Dijkstra algorithm which the engine class uses to 
	 * calculate the shortest distance between two cities/vertices
	 */
	private Dijkstra dijkstra;
	
	
	
		
	/**
	 * This is the Engine class constructor that instantiates the fields of this class with their respective values
	 * @param ui isntance of the UserInterface class
	 */
	public Engine(UserInterface ui){
		this.ui = ui;
		cityNodeList = new ArrayList<>();
		roadNodeList = new ArrayList<>();
	}
	
	/**
	 * This is the start method that starts the whole program. It is called on by the main method. It then scans 
	 * the files that contain the cities and the codes, then creates a graph using the data it scanned. It then 
	 * runs the run method which goes runs the program
	 * @throws IOException This may throw a file not found exception
	 */
	public void start() throws IOException{
		
		scanCity();
		scanRoad();
		createGraph(getRoadNodes());
		run();
		
	}
	
	
	/**
	 * This is the run method. It is a while loop that runs until the user decides to exit the program by entering
	 * "E" as the input. The whole program runs from this while loop and this method is connected to all other 
	 * functions of the program
	 */
	private void run(){
		
		ui.menu(1);
		boolean run = true;
		while(run) {
			
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
						}else if(input[0].equalsIgnoreCase("D")){
							ui.menu(4);
							shortestPath();
						}else if(input[0].equalsIgnoreCase("I")){
							ui.menu(5);
							insertRoad();
						}else if(input[0].equalsIgnoreCase("R")){
							ui.menu(4);
							removeRoad();
						}else if(input[0].equalsIgnoreCase("H")){
							ui.menu(1);
						}else if(input[0].equalsIgnoreCase("E")){
							ui.menu(6);
							System.exit(0);
						}else
							ui.error(1);
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
	}
	
	/**
	 * This method creates a graph using an adjacency matrix using the data that is scanned from both the city data 
	 * file and the road data file
	 * @param roadNodes The array that contains the initial roads that exist
	 * @throws FileNotFoundException
	 */
	private void createGraph(Road[] roadNodes) throws FileNotFoundException {
		
		graph = new Digraph(getCityNodes().length, getCityNodes());
		
		for(int i = 1; i < roadNodes.length; i++) 
			graph.addEdge(roadNodes[i].getFrom().getCityNumber(), roadNodes[i].getTo().getCityNumber(), roadNodes[i].getWeight());
	}
	
	/**
	 * This is the query method. If the user wants information on a city that exist within the graph, this method calls
	 * on the cityExist() method that gets that certain city and sends that city to the UserInterface class so that it
	 * can be printed out to the user.
	 */
	private void query(){
		
		String[] searchCityCode = null;
		
			@SuppressWarnings("unused")
			int integerTest = -1;//Integer is used to test of input from UserInterface class is a number
			
			searchCityCode = ui.getInput();
			/*
			 * This if statement checks if the string array of inputs is null. If it is null that
			 * means that the user input did not meet the first set of requirements to be correct in 
			 * the UserInterface getInput() method. Reasons that this is null are that the user 
			 * had entered a special character or did not seperate inputs using " ".
			 */
			if(searchCityCode != null) {
				/*
				 * This try catch block is used to test the input can be parsed into a number.
				 * If it can then the user will be asked to input a non number input. If the 
				 * input cannot be parsed it means that the input is not a number then is checked
				 * for other errors
				 */
				try {
					integerTest = Integer.parseInt(searchCityCode[0]);
					ui.error(10);
					ui.error(9);
				} catch (NumberFormatException e) {
					/*
					 * This if statement checks to see if the amount of inputs the user inputed. If the size of the input 
					 * array does not match this if statement, it means the user entered the wrong amount of inputs required
					 * for this certain command
					 */
					if(searchCityCode.length == 1) {
						City searchingCity = cityExist(searchCityCode[0]);
						if(searchingCity != null) {
							ui.printCity(searchingCity);
						}else { //Rest of method sends different error messages depending on the error committed
							ui.error(4);
							ui.error(9);
						}	
					}else {
						ui.error(3);
						ui.error(9);
					}
				}
			}else {
				ui.error(11);
				ui.error(9);
			}
	}
	
	/**
	 * This is the shortest path that calls upon the shortestPath() method of the Dijkstra class. It then receives a 
	 * an array list of the shortest path from the Dijkstra class and passes it to the UserInterface class so that it 
	 * can print it out to the user
	 */
	private void shortestPath() {
		
		@SuppressWarnings("unused")
		int integerTest = -1; //Integer is used to test of input from UserInterface class is a number
		String[] searchCityCodes = null; 
				
		searchCityCodes = ui.getInput(); //Receives input
		/*
		 * This if statement checks if the string array of inputs is null. If it is null that
		 * means that the user input did not meet the first set of requirements to be correct in 
		 * the UserInterface getInput() method. Reasons that this is null are that the user 
		 * had entered a special character or did not seperate inputs using " ".
		 */
		if(searchCityCodes != null) {
			/*
			 * This try catch block is used to test the input can be parsed into a number.
			 * If it can then the user will be asked to input a non number input. If the 
			 * input cannot be parsed it means that the input is not a number then is checked
			 * for other errors
			 */
			try {
				integerTest = Integer.parseInt(searchCityCodes[0]);
				integerTest = Integer.parseInt(searchCityCodes[1]);
				ui.error(10);
				ui.error(9);
			} catch (NumberFormatException e1){
				/*
				 * This if statement checks to see if the amount of inputs the user inputed. If the size of the input 
				 * array does not match this if statement, it means the user entered the wrong amount of inputs required
				 * for this certain command
				 */
				if(searchCityCodes.length == 2 ) {
			
					City from = cityExist(searchCityCodes[0]);
					City to = cityExist(searchCityCodes[1]);
					
					//This if statement checks to see that the cities inputed exist
					if(from != null && to != null) {
						//This if statement checks if from and to cities are the same
						if(from != to && to != from) {
							dijkstra = new Dijkstra(getCityNodes(), graph, from.getCityNumber(), to.getCityNumber()); 
							ui.printShortestPath(from.getCityName(), to.getCityName(), dijkstra.dikstra()[to.getCityNumber()], dijkstra.getPath());
						}else {//Rest of method sends different error messages depending on the error committed
							ui.error(13);
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
					ui.error(9);
				}
			}
		}else {
			ui.error(2);
			ui.error(9);
		}
	}
	
	
	
	/**
	 * This method is called upon whenever a part of the program needs to check whether a city exist and if it does
	 * It will traverse through an array of cities and return the city if it matches
	 * @param searchCityCode the unique code of the city being searched
	 * @return the searched city
	 */
	private City cityExist(String searchCityCode){
		
		City[] cities = getCityNodes();
		City searchCity = null;
		
		for(int i = 1; i < cities.length; i++)
			if(cities[i].getCityCode().equalsIgnoreCase(searchCityCode))
				searchCity = cities[i];
	
		return searchCity;
	}
	
	/**
	 * This method is called upon when the user wants to insert a road/edge into the graph. The Method will first
	 * check if a road between the two cities already exist and if there is no road between those two cities, the 
	 * road will be added in the appropriate index of the adjacency matrix that represents the graph of cities
	 */
	private void insertRoad(){
		
		@SuppressWarnings("unused")
		int integerTest = -1; //Integer is used to test of input from UserInterface class is a number
		
		String[] roadAdded = null;
		
		roadAdded = ui.getInput();
		/*
		 * This if statement checks if the string array of inputs is null. If it is null that
		 * means that the user input did not meet the first set of requirements to be correct in 
		 * the UserInterface getInput() method. Reasons that this is null are that the user 
		 * had entered a special character or did not seperate inputs using " ".
		 */
			if(roadAdded != null) {
				/*
				 * This try catch block is used to test the input can be parsed into a number.
				 * If it can then the user will be asked to input a non number input. If the 
				 * input cannot be parsed it means that the input is not a number then is checked
				 * for other errors
				 */
				try {
					integerTest = Integer.parseInt(roadAdded[0]);
					integerTest = Integer.parseInt(roadAdded[1]);
					ui.error(10);
					ui.error(9);
				} catch (NumberFormatException e1) {
					/*
					 * This if statement checks to see if the amount of inputs the user inputed. If the size of the input 
					 * array does not match this if statement, it means the user entered the wrong amount of inputs required
					 * for this certain command
					 */
					if(roadAdded.length == 3) {
						City from = cityExist(roadAdded[0]);
						City to = cityExist(roadAdded[1]);
						int distance = Integer.MIN_VALUE;
						try {
							distance = Integer.parseInt(roadAdded[2]);
						} catch (NumberFormatException e) {
							ui.error(7);
						}
						/*
						 * This part of the method checks to see if the cities and road that are being entered
						 * exist and also checks to see if the distance is not a negative number
						 */
						if(from != null && to != null && distance != Integer.MIN_VALUE && distance > 0) {
							//This if statement checks if from and to cities are the same
							if(from != to && to != from) {
								//This if statement checks if a road already exist
								if(graph.getEdge(from.getCityNumber(), to.getCityNumber()) == 0) { 
									graph.addEdge(from.getCityNumber(), to.getCityNumber(), distance);
									ui.printGraphManipulation(from.getCityName(), to.getCityName(), distance, 1);
								}else { //Rest of method sends different error messages depending on the error committed
									ui.error(1,from.getCityName(),to.getCityName());
									ui.error(9);
								}
							}else {
								ui.error(13);
								ui.error(9);
							}
						}else {
							if(from == null)
								ui.error(5);
							if(to == null)
								ui.error(6);
							if(distance < 0 && distance != Integer.MIN_VALUE)
								ui.error(8);
							ui.error(9);
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
		int integerTest = -1; //Integer is used to test of input from UserInterface class is a number
		String[] roadAdded = null; 
		
		roadAdded = ui.getInput();
		/*
		 * This if statement checks if the string array of inputs is null. If it is null that
		 * means that the user input did not meet the first set of requirements to be correct in 
		 * the UserInterface getInput() method. Reasons that this is null are that the user 
		 * had entered a special character or did not separate inputs using " ".
		 */
		if(roadAdded != null) {
			/*
			 * This try catch block is used to test the input can be parsed into a number.
			 * If it can then the user will be asked to input a non number input. If the 
			 * input cannot be parsed it means that the input is not a number then is checked
			 * for other errors
			 */
			try {
				integerTest = Integer.parseInt(roadAdded[0]);
				integerTest = Integer.parseInt(roadAdded[1]);
				ui.error(10);
				ui.error(9);
			} catch (NumberFormatException e1) {
				/*
				 * This if statement checks to see if the amount of inputs the user inputed. If the size of the input 
				 * array does not match this if statement, it means the user entered the wrong amount of inputs required
				 * for this certain command
				 */
				if(roadAdded.length == 2) {
					City from = cityExist(roadAdded[0]);
					City to = cityExist(roadAdded[1]);
					/*
					 * This part of the method checks to see if the cities and road that are being entered
					 * exist and also checks to see if the distance is not a negative number
					 */
					if(from != null && to != null) {
						//This if statement checks if from and to cities are the same
						if(from != to && to != from) {
							//This if statement checks if a road exists
							if(graph.getEdge(from.getCityNumber(), to.getCityNumber()) != 0) {
								graph.removeEdge(from.getCityNumber(), to.getCityNumber());
								ui.printGraphManipulation(from.getCityName(), to.getCityName(), 0, 2);
							}else {//Rest of method sends different error messages depending on the error committed
								ui.error(2,from.getCityName(),to.getCityName());
								ui.error(9);
							}
						}else {
							ui.error(13);
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
	
	/**
	 * This method contains the data of cities from the file read
	 * @return an array of cities read from the file
	 */
	private City[] getCityNodes() {
		City[] cityNodes = new City[cityNodeList.size()+1];
		for(int i = 1; i < cityNodes.length; i++)
			cityNodes[i] = cityNodeList.get(i-1);
		
		return cityNodes;
	}
	
	/**
	 * This method contains the data of the roads from the file read
	 * @return an array of roads read from the file
	 */
	private Road[] getRoadNodes() {
		Road[] roadNodes = new Road[roadNodeList.size()+1];
		for(int i = 1; i < roadNodes.length; i++)
			roadNodes[i] = roadNodeList.get(i-1);
		
		return roadNodes;
		
	}
	
	/**
	 * This is the scanCity() method that uses the scanner class to read from a file and pass the information
	 * of that file over to the program. It begins with reading each line and while reading each line it will 
	 * read each separation and input that section of the line into the approached value for the city.Every 
	 * a line is read, a new city object is made and put into an array list. Array list was used because to 
	 * make this program compatible with other files that may have a different amount of lines/cities
	 * @throws FileNotFoundException
	 */
	private void scanCity() throws FileNotFoundException {
		try {
			readFile = new Scanner(new File("city(2).dat"));
		} catch (FileNotFoundException e) {
			
			ui.error(12);
			ui.menu(6);
			System.exit(0);
		}
		if(cityNodeList.size() != 0)
			cityNodeList = new ArrayList<>();
	
		while(readFile.hasNext()) {
			int cityNumber = Integer.parseInt(readFile.next()); 
			String cityCode = readFile.next();
			String fullCityName = null;
			/**
			 * This if statement pertains to the city data file that was given by the .
			 * A problem that was encountered was that some cities have to words in their
			 * name and if read line by line with space by space then the cities would be out 
			 * of order and the program will crash. This if checks if the line being read contains 
			 * a certain city number. Every city has its own unique city number and these city numbers 
			 * that are being checked are the cities with two words as a name. Whenever one of these cities
			 * are being read, the scanner class will read two words for the city name instead of one.
			 */
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
	
	/**
	 * This is the scanRoad() method that uses the scanner class to read from a file and pass the information
	 * of that file over to the program. It begins with reading each line and while reading each line it will 
	 * read each separation and input that section of the line into the approached value for the road.Every 
	 * a line is read, a new road object is made and put into an array list. Array list was used because to 
	 * make this program compatible with other files that may have a different amount of lines/roads
	 * @throws FileNotFoundException
	 */
	private void scanRoad() {
		try {
			readFile = new Scanner(new File("road(2).dat"));
		} catch (FileNotFoundException e) {
			
			ui.error(12);
			ui.menu(6);
			System.exit(0);
		}
		while(readFile.hasNext()) {
			int from = Integer.parseInt(readFile.next());
			int to = Integer.parseInt(readFile.next());
			int distance = Integer.parseInt(readFile.next());
	
			roadNodeList.add(new Road(getCityNodes()[from], getCityNodes()[to], distance));
		}
	}
		
}