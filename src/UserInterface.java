import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the UserInterface class. This class is the class that takes input and displays output 
 * to the console and the user. All interactions with the user happen in this class. This class
 * takes is called on by the Engine class whenever data needs the to be printed out. This class 
 * also returns user input back to the Engine class to be used however it needs to.
 * @author blayala
 *
 */
public class UserInterface {

	/**
	 * This is the instance of the Scanner class that is used to accept user input
	 */
	private Scanner text;
	/**
	 * Instance of the Pattern class used to check if user input contains any special characters
	 */
	private Pattern pattern;
	/**
	 * Instance of the Matcher class used to check if user input contains any special characters
	 */
	private Matcher matcher;
	
	/**
	 * This is the constructor for the UserInterace class. It instantiates the fields with their respective values.
	 */
	public UserInterface() {
		text = new Scanner(System.in);
		pattern = Pattern.compile("[a-zA-Z0-9]*");
	}
	
	/**
	 * This method receives input from the user. Since project requirements state that all inputs must be 
	 * inputed in one line, an array of strings was used to represent the input of the user. This method 
	 * takes in a string, then using the split method splits the string whenever there is a " " and puts
	 * those split array pieces into an array of strings. The pattern and matcher classes are then used 
	 * to check if the array of strings contains any special characters as this interferes with how the data
	 * is split and how it is read by the engine class. All except the 3rd index are checked because of the
	 * possibility of that being a negative number. More on that is explained in the engine error checking 
	 * documentation. If the string array contains a special character then the method returns null and the 
	 * engine will call on this method again for a valid input
	 * @return
	 */
	public String [] getInput() {
		
		String [] correctArrayOfString = null; //array of strings with no special character except for possibly the 3rd index
		
		String [] arrayOfString = text.nextLine().split(" "); //take in string from user and split it
		
		boolean correct = true;
		
		for(int i = 0; i < arrayOfString.length; i++) {
			/*
			 * Here the pattern class and matcher class are checking
			 * over each index in arrayOfString and checks for any
			 * special characters
			 */
			matcher = pattern.matcher(arrayOfString[i]);
			if(!matcher.matches()) {
				for(int j = 0; j < arrayOfString.length; j++) {
					matcher = pattern.matcher(arrayOfString[j]);
					
					if(j != 2 && !matcher.matches()) //3rd index is skipped and checked later in engine for possible negative number
						correct = false;
				}
			}	
		}
		
		if(correct) 
			correctArrayOfString = arrayOfString; // if arrayOfString contains no special characters then its equaled to the correctArrayOfString
		
		return correctArrayOfString;
	}
	
	/**
	 * This method is the menu method. It prints out all the commands that the user asks for. It consist of a switch 
	 * Statement that takes in a operation number. That operation number dictates what command the user has inputed and 
	 * what information must be displayed out to the user for the program to continue.
	 * @param operation the number to whichever statement needs to be printed our to the user
	 */
	public void menu(int operation){
		
		switch(operation){
		
		case 1: System.out.println("\nQ Query the city information by entering the city code.\n"
									+ "D Find the minimum distance between two cities.\n"
									+ "I Insert a road by entering two city codes and distance.\n"
									+ "R Remove an existing road by entering two city codes.\n"
									+ "H Display this message.\n"
									+ "E Exit.");
				break;
		case 2: System.out.print("Command?: ");
				break;
		case 3: System.out.print("City Code: ");
				break;
		case 4: System.out.print("City Codes: ");
				break;
		case 5: System.out.print("City Codes and distance: ");
				break;
		case 6: System.out.print("Program has exited.\n" 
								  + "Thank you for using my program");
				break;

		}
		
	}
	
	/**
	 * This method prints out information to the user the pertains to the manipulation of the graph. 
	 * Depending on weather the user added or deleted a(n) edge/road from the graph, the method will
	 * display the type of change and the cities and/or road that were involved in that change
	 * @param from
	 * @param to
	 * @param distance
	 * @param operation
	 */
	public void printGraphManipulation(String from, String to, int distance, int operation) {
		
		switch(operation) {
		
		case 1: System.out.println("You have inserted a road from " + from + " to " + to + " with a distance of " + distance + ".\n");
				break;
		case 2: System.out.println("You have deleted the road from " + from + " to " + to + ".\n");
				break;
		
		}
	
	}
	
	/**
	 * This prints out the shortest path created by the Dijkstra class and its shortest path method which 
	 * is represented by an array list.
	 * @param from the departing city
	 * @param to the destination city
	 * @param distance the weight of the path
	 * @param path the array list containing the shortest path
	 */
	public void printShortestPath(String from, String to, int distance, ArrayList<City> path) {
		
		System.out.println("The minimum distance between " + from + " and " + to + " is " + distance + " through the route:");
		
		for(int i = 0; i < path.size(); i ++)
			if(i == 0)
				System.out.print(path.get(i).getCityCode());
			else
				System.out.print( ", " + path.get(i).getCityCode());
	
		System.out.println("\n");
	}
	
	/**
	 * Prints out the information contained within a city node whenever the user asks for
	 * a query on the city
	 * @param city the city being queried
	 */
	public void printCity(City city) {
		System.out.println(city + "\n");
	}
	
	/**
	 * This method is called on whenever the Engine class finds an error in the users input. 
	 * A switch statement is used to accommodate multiple possible errors that may occur
	 * @param operation type of error that was found
	 */
	public void error(int operation){
		
		switch(operation) {
		
		case 1: System.out.println("Error: Invalid input. Please enter a command from the menu.\n"
									+ "To see the menu, press H\n");
				break;
		case 2: System.out.println("Error: Spaces must be used to seperate inputs.\n"
								   + "       Inputs can not contain any special characters\n"
<<<<<<< Updated upstream
								   + "Ex: I AN BO 78\n" 
								   + "Ex: !@#$%^&*-=+ etc. are not allowed");
=======
								   + "Ex: D CH PH" 
								   + "!@#$%^&* ect. are not allowed");
>>>>>>> Stashed changes
				break;
		case 3: System.out.println("Error: Wrong amount of inputs were entered for this command\n"
								    + "Ex: Q AN\n"
								    + "    D AN BK\n"
								    + "    I AN BO 45\n"
								    + "    R AN BK");
				break;
		case 4: System.out.println("Error: City you have entered does not exist");
				break;
		case 5: System.out.println("Error: The departure city you inputed does not exist");
				break;
		case 6: System.out.println("Error: The destination city you inputed does not exist");
				break;
		case 7: System.out.println("Error: Distance entered must be a number");
				break;
		case 8: System.out.println("Error: Distance can not be negative");
				break;
		case 9: System.out.println("Please try again\n");
				break;
		case 10:System.out.println("Error: Input can not be a number");
				break;
		case 11: System.out.println("Error: Input can not have any special characters\n"
									+ "Ex: !@#$%^&*-=+ etc. are not allowed");
				break;
		case 12:System.out.println("Error: File not found");
				break;
		case 13:System.out.println("Error: Cities entered must be different\n"
								   + "Ex: AN BK");
		}
			
	}
	
	/**
	 * This is another overloaded error method that also displays another type of error that may occur.
	 * This error only occurs when the edge/road the user wants to input/remove  already exist/doesn't exist
	 * @param operation the type of error 
	 * @param from departing city
	 * @param to destination city
	 */
	public void error(int operation, String from, String to) {
		
		switch(operation) {
		
			case 1: System.out.println("Error: The road from " + from + " to " + to + " already exist.");
					break;
			case 2: System.out.println("Error: The road from " + from + " to " + to + " doesn't exist.");
					break;
			case 3: System.out.println("Please try again\n");
					break;
			}
		
	}
		
}
