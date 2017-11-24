
/**
 * This is the city class. The city class acts as this programs vertex
 * class and contains all the different attributes related to the city
 * that it represents
 * @author blayala
 *
 */
public class City {
	
	/**
	 * This is the city number of the city. Every city has a unique city number
	 */
	private int cityNumber;
	/**
	 * This is the city code of the city. Every city has a unique city code
	 */
	private String cityCode;
	/**
	 * This is the full city name of the city is represents
	 */
	private String fullCityName;
	/**
	 * This the population of the city
	 */
	private int population;
	/**
	 * This is the elevation of the city
	 */
	private int elevation;
	
	/**
	 * This is the constructor that instantiates the fields with their respective values
	 * @param cityNumber city's city number
	 * @param cityCode city's city code
	 * @param fullCityName city's full name
	 * @param population city's population
	 * @param elevation city's elevation
	 */
	public City(int cityNumber, String cityCode, String fullCityName, int population, int elevation) {
		
		this.cityNumber = cityNumber;
		this.cityCode = cityCode;
		this.fullCityName = fullCityName;
		this.population = population;
		this.elevation = elevation;
		
	}
	
	/**
	 * gets the city's number
	 * @return city number
	 */
	public int getCityNumber() {
		return cityNumber;
	}
	/**
	 * gets the city's code
	 * @return city code
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * gets the city's full city name
	 * @return full city name
	 */
	public String getCityName() {
		return fullCityName;
	}
	/**
	 * Overide of the toString method. Translates the city object into a readable string
	 */
	public String toString() {
		return "" + cityNumber + " " + cityCode + " " + fullCityName + " " + population + " " + elevation;
	}
	
}
