import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {

	private Scanner input;
	private Scanner text;
	private Pattern pattern;
	private Matcher matcher;
	
	public UserInterface() {
		input = new Scanner(System.in);
		text = new Scanner(System.in);
		pattern = Pattern.compile("[a-zA-Z0-9]*");
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
		
		case 1: System.out.println("You have inserted a road from " + from + " to " + to + " with a distance of " + distance + ".");
				break;
		case 2: System.out.println("You have deleted the road from " + from + " to " + to + ".");
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
	
		System.out.println();
	}
	public String [] getInput() {
		
		String [] correctArrayOfString = null;
		
		String [] arrayOfString = text.nextLine().split(" ");
		
		int possibleNegativeNumber = Integer.MIN_VALUE;
		
		
		
		
		boolean correct = true;
		
		for(int i = 0; i < arrayOfString.length; i++) {
			
			matcher = pattern.matcher(arrayOfString[i]);
			if(!matcher.matches()) {
				for(int j = 0; j < arrayOfString.length; j++) {
					matcher = pattern.matcher(arrayOfString[j]);
					
					
					if(j != 2 && !matcher.matches())
						correct = false;
					//else {
						//try {
							//possibleNegativeNumber = Integer.parseInt(arrayOfString[2]);
					//	} catch (NumberFormatException e) {
							
							//if(!matcher.matches())
								//correct = false;
							
						//}
					//}
				}
			}
				
		}
		
		if(correct)
			correctArrayOfString = arrayOfString;
		
		return correctArrayOfString;
	}
	
	
	
	public int getPossibleNegativeNumber(int possibleNegativeNumber) {
		return possibleNegativeNumber;
	}
	
	public void error(int operation){
		
		switch(operation) {
		
		case 1: System.out.println("Error: Invalid input. Please enter a command from the menu.\n"
									+ "       To see the menu, press H");
				break;
		case 2: System.out.println("Error: Spaces must be used to seperate inputs");
				break;
		case 3: System.out.println("Error: Wrong amount of inputs were entered for this command");
				break;
		case 4: System.out.println("Error: City you have entered does not Exist");
				break;
		case 5: System.out.println("Error: The departure city you inputed does not exist");
				break;
		case 6: System.out.println("Error: The destination city you inputed does not exist");
				break;
		case 7: System.out.println("Error: Distance entered must be a number");
				break;
		case 8: System.out.println("Error: Distance error can not be negative");
				break;
		case 9: System.out.println("       Please try again");
				break;
		}
			
	}
	
	public void error(int operation, String from, String to) {
		
switch(operation) {
		
		case 1: System.out.println("Error: The road from " + from + " to " + to + " already exist.");

				break;
		case 2: System.out.println("Error: The road from " + from + " to " + to + " doesn't exist.");
				break;
		
		case 3: System.out.println("       Please try again");
				break;
		}
		
	}
	
	public void print(String [] arrayOfString) {
		
		if(arrayOfString != null) {
			for(int i = 0; i < arrayOfString.length; i++)	
				System.out.println(arrayOfString[i]);
		}
		else 
		System.out.println("Error");
	}
	
}
