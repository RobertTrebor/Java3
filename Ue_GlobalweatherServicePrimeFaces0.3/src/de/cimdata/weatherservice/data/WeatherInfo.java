package de.cimdata.weatherservice.data;

public class WeatherInfo {
	
	
	private String city ="";
	private Weather weather;
	
	public WeatherInfo(String city, Weather weather) {
		super();
		this.city = city;
		this.weather = weather;
	}
	public WeatherInfo() {
		// TODO Auto-generated constructor stub
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	


}
