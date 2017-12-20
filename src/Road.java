
/**
 * This is the road class. It acts as this programs edge class. The class
 * is a representations of a weighted edge/road which contains its attributes.
 * @author blayala
 *
 */
public class Road<N,T> {

	/**
	 * The departing city
	 */
	private T from;
	/**
	 * The destination city
	 */
	private T to;
	/**
	 * The distance between the from and to city
	 */
	private N weight;
	
	/**
	 * This is the constructor for the road that instantiates all the fields with their respective values
	 * @param from the departing city
	 * @param to the destination city
	 * @param weight the amount of distance bwtween the from and to city
	 */
	public Road(T from, T to, N weight) {
		
		this.from = from;
		this.to = to;
		this.weight = weight;
		
	}
	
	/**
	 * gets the departing city
	 * @return from city
	 */
	public T getFrom() {
		return from;
	}
	/**
	 * gets the destination city
	 * @return to ctiy
	 */
	public T getTo() {
		return to;
	}
	/**
	 * gets the weight
	 * @return distance between from and to 
	 */
	public N getWeight() {
		return weight;
	}
	/**
	 * Overide of the toString method. Translates the node object into a readable string
	 */
	public String toString() {
		return "From: " + from + "\n to: " + to + "\n edge: " + weight + "\n";
	}
	
}
