
public class RoadNode {

	int from;
	int to;
	int edge;
	
	public RoadNode(int from, int to, int edge) {
		
		this.from = from;
		this.to = to;
		this.edge = edge;
		
	}
	
	public String toString() {
		return "From: " + from + "\nto: " + to + "\nedge: " + edge;
	}
	
}
