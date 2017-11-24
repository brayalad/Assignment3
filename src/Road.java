
/**
 * This is the road class. It acts as this programs edge class. The class
 * is a representations of a weighted edge/road which contains its attributes.
 * @author blayala
 *
 */
public class Road {

	/**
	 * The departing city
	 */
	private City from;
	/**
	 * The destination city
	 */
	private City to;
	/**
	 * The distance between the from and to city
	 */
	private int weight;
	
	/**
	 * This is the constructor for the road that instantiates all the fields with their respective values
	 * @param from the departing city
	 * @param to the destination city
	 * @param weight the amount of distance bwtween the from and to city
	 */
	public Road(City from, City to, int weight) {
		
		this.from = from;
		this.to = to;
		this.weight = weight;
		
	}
	
	/**
	 * gets the departing city
	 * @return from city
	 */
	public City getFrom() {
		return from;
	}
	/**
	 * gets the destination city
	 * @return to ctiy
	 */
	public City getTo() {
		return to;
	}
	/**
	 * gets the weight
	 * @return distance between from and to 
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * Overide of the toString method. Translates the node object into a readable string
	 */
	public String toString() {
		return "From: " + from + "\n to: " + to + "\n edge: " + weight + "\n";
	}
	
}
