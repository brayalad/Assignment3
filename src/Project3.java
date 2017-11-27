import java.io.*;

/**
 * This is Project 3 for CS 241.03. The purpose of this project is to practice using the Dijkstra algorithm that
 * calculates the shorts distance from a single source to all other vertices in the graph. This program takes in 
 * data that it reads from two files provided by the professor that contains information regarding the cities that
 * act as vertices and roads that act as edges. The program then uses that data and creates a directed weighted graph 
 * for the user to traverse. The program allows the user to search for a city using its city code and query the info
 * about that city. The User is also allowed to calculated the shortest distance and path between two cities that are
 * chosen by the user. The user is also allowed to manipulate the graph by either adding non-existing roads/edges or 
 * removing already existing roads and edges.
 * @author blayala
 *
 */
public class Project3 {

	/**
	 * This is the main method that is required by the java syntax in order for a program to run.
	 * Nothing is done in this method except the creation of instances. A instance of the Engine
	 * class is made, which is the class that runs the program and a instance of the UserInterface 
	 * class is passed into its constructor so that the engine will be able to communicate more 
	 * Efficiently with the UserInterface class
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
			
		Engine engine = new Engine(new UserInterface());
		engine.start();

	}

}
