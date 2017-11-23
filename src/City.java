

public class City {
	
	private int cityNumber;
	private String cityCode;
	private String fullCityName;
	private int population;
	private int elevation;
	
	
	public City(int cityNumber, String cityCode, String fullCityName, int population, int elevation) {
		
		this.cityNumber = cityNumber;
		this.cityCode = cityCode;
		this.fullCityName = fullCityName;
		this.population = population;
		this.elevation = elevation;
		
	}
	
	public int getCityNumber() {
		return cityNumber;
	}
	public String getCityCode() {
		return cityCode;
	}
	public String getCityName() {
		return fullCityName;
	}
	
	
	
	public String toString() {
		return "" + cityNumber + " " + cityCode + " " + fullCityName + " " + population + " " + elevation;
	}
	
}
