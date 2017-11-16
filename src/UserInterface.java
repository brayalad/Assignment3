import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {

	private Scanner input;
	private Pattern pattern;
	private Matcher matcher;
	
	public UserInterface() {
		input = new Scanner(System.in);
		pattern = Pattern.compile("[a-zA-Z0-9]*");
	}
	
	public String [] getInput() {
		
		
		
		System.out.println("Please input command: ");
		
		String [] correctArrayOfString = null;
		
		String [] arrayOfString = input.nextLine().split(" ");
		
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
	
	public void print(String [] arrayOfString) {
		
		if(arrayOfString != null) {
			for(int i = 0; i < arrayOfString.length; i++)	
				System.out.println(arrayOfString[i]);
		}
		else 
		System.out.println("Error");
	}
	
}
