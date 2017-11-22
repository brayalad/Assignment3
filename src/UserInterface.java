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
									+ "E Exit.\n");
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
	
	
	
	
	public String [] getInput() {
		
		String [] correctArrayOfString = null;
		
		String [] arrayOfString = text.nextLine().split(" ");
		
		boolean correct = true;
		
		for(int i = 0; i < arrayOfString.length; i++) {
			
			matcher = pattern.matcher(arrayOfString[i]);
			if(!matcher.matches()) {
				for(int j = 0; j < arrayOfString.length; j++) {
					matcher = pattern.matcher(arrayOfString[j]);
					if(!matcher.matches())
						correct = false;
				}
			}
				
		}
		
		if(correct)
			correctArrayOfString = arrayOfString;
		
		return correctArrayOfString;
	}
	
	public void error(){
		System.out.println("Error");
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
