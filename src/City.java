
/**
 * This is the city class. The city class acts as this programs vertex
 * class and contains all the different attributes related to the city
 * that it represents
 * @author blayala
 *
 */
public class City<N,T> {
	
	/**
	 * This is the city number of the city. Every city has a unique city number
	 */
	private N cityNumber;
	/**
	 * This is the city code of the city. Every city has a unique city code
	 */
	private T cityCode;
	/**
	 * This is the full city name of the city is represents
	 */
	private T fullCityName;
	/**
	 * This the population of the city
	 */
	private N population;
	/**
	 * This is the elevation of the city
	 */
	private N elevation;
	
	/**
	 * This is the constructor that instantiates the fields with their respective values
	 * @param cityNumber city's city number
	 * @param cityCode city's city code
	 * @param fullCityName city's full name
	 * @param population city's population
	 * @param elevation city's elevation
	 */
	public City(N cityNumber, T cityCode, T fullCityName, N population, N elevation) {
		
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
	public N getCityNumber() {
		return cityNumber;
	}
	/**
	 * gets the city's code
	 * @return city code
	 */
	public T getCityCode() {
		return cityCode;
	}
	/**
	 * gets the city's full city name
	 * @return full city name
	 */
	public T getCityName() {
		return fullCityName;
	}
	/**
	 * Overide of the toString method. Translates the city object into a readable string
	 */
	public String toString() {
		return "" + cityNumber + " " + cityCode + " " + fullCityName + " " + population + " " + elevation;
	}
	
}
