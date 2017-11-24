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
	 * Instance of the Pattern class used to check if user input contains any special characters
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
	
	public void menu(int operation){
		
		switch(operation){
		
		case 1: System.out.println("Q Query the city information by entering the city code.\n"
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

		}
		
	}
	
	public void printGraphManipulation(String from, String to, int distance, int operation) {
		
		switch(operation) {
		
		case 1: System.out.println("You have inserted a road from " + from + " to " + to + " with a distance of " + distance + ".\n");
				break;
		case 2: System.out.println("You have deleted the road from " + from + " to " + to + ".\n");
				break;
		
		}
	
	}
	
	public void printShortestPath(String from, String to, int distance, ArrayList<City> path) {
		
		System.out.println("The minimum distance between " + from + " and " + to + " is " + distance + " through the route:");
		
		for(int i = 0; i < path.size(); i ++)
			if(i == 0)
				System.out.print(path.get(i).getCityCode());
			else
				System.out.print( ", " + path.get(i).getCityCode());
	
		System.out.println("\n");
	}
	
	public void printCity(City city) {
		System.out.println(city + "\n");
	}
	
	public void error(int operation){
		
		switch(operation) {
		
		case 1: System.out.println("Error: Invalid input. Please enter a command from the menu.\n"
									+ "To see the menu, press H");
				break;
		case 2: System.out.println("Error: Spaces must be used to seperate inputs.\n"
								   + "       Inputs can not contain any special characters");
				break;
		case 3: System.out.println("Error: Wrong amount of inputs were entered for this command");
				break;
		case 4: System.out.println("Error: City you have entered does not exist");
				break;
		case 5: System.out.println("Error: The departure city you inputed does not exist");
				break;
		case 6: System.out.println("Error: The destination city you inputed does not exist");
				break;
		case 7: System.out.println("Error: Distance entered must be a number");
				break;
		case 8: System.out.println("Error: Distance error can not be negative");
				break;
		case 9: System.out.println("Please try again\n");
				break;
		case 10:System.out.println("Error: Input can not be a number");
				break;
		case 11: System.out.println("Error: Input can not have any special characters");
				break;
		}
			
	}
	
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
