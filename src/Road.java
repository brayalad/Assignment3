
public class Road {

	private City from;
	private City to;
	private int weight;
	
	public Road(City from, City to, int weight) {
		
		this.from = from;
		this.to = to;
		this.weight = weight;
		
	}
	
	public City getFrom() {
		return from;
	}
	public City getTo() {
		return to;
	}
	public int getWeight() {
		return weight;
	}

	
	public String toString() {
		return "From: " + from + "\n to: " + to + "\n edge: " + weight + "\n";
	}
	
}
