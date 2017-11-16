
public class CityNode {
	
	int cityNumber;
	String cityCode;
	String fullCityName;
	int population;
	int elevation;
	
	public CityNode(int cityNumber, String cityCode, String fullCityName, int population, int elevation) {
		
		this.cityNumber = cityNumber;
		this.cityCode = cityCode;
		this.fullCityName = fullCityName;
		this.population = population;
		this.elevation = elevation;
		
	}
	
	public String toString() {
		return " " + cityNumber + " " + cityCode + " " + fullCityName + " " + population + " " + elevation;
	}
	
}
