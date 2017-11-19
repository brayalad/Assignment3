import java.util.ArrayList;

public class City {
	
	private int cityNumber;
	private String cityCode;
	private String fullCityName;
	private int population;
	private int elevation;
	private ArrayList<Road> neighbors;
	
	public City(int cityNumber, String cityCode, String fullCityName, int population, int elevation) {
		
		this.cityNumber = cityNumber;
		this.cityCode = cityCode;
		this.fullCityName = fullCityName;
		this.population = population;
		this.elevation = elevation;
		neighbors = new ArrayList<>();
	}
	
	public void addNeighbor(Road road) {
		neighbors.add(road);
	}
	
	public Road getNeigbor(int index) {
		return neighbors.get(index);
	}
	
	public void removeNeighbor(int index) {
		neighbors.remove(index);
	}
	
	public int getCityNumber() {
		return cityNumber;
	}
	
	public ArrayList<Road> getNeighbors(){
		return neighbors;
	}
	
	
	public String toString() {
		return " " + cityNumber + " " + cityCode + " " + fullCityName + " " + population + " " + elevation;
	}
	
}
