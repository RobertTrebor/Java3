package de.cimdata.weatherservice.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.cimdata.weatherservice.data.WeatherInfo;
import de.cimdata.weatherservice.exception.DataNotFoundException;
import de.cimdata.weatherservice.exception.WetterWebServiceException;
import de.cimdata.weatherservice.facesutil.CountryUtil;
import de.cimdata.weatherservice.xmlservice.ServiceHandler;

@SessionScoped
@ManagedBean
public class WeatherBean {

	private List<String> cityList = new ArrayList<>();
	private String currentCity = "";
	private String currentCountry = "";
	private ServiceHandler service;

	private WeatherInfo weatherInfo;

	public WeatherBean() {
		service = new ServiceHandler();
		setWeatherInfo(new WeatherInfo());
	}

	public List<String> getCityList() {
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}

	public WeatherInfo getWeatherInfo() {
		return weatherInfo;
	}

	public void setWeatherInfo(WeatherInfo weatherInfo) {
		this.weatherInfo = weatherInfo;
	}

	public void loadCityList() {
		
		try{
		cityList = service.getCityNames(currentCountry);
		
		}catch(WetterWebServiceException e){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Connection Timeout!","No Connection..."));
		}
	}

	public void loadWeatherInfo(){
		WeatherInfo info=null;
		try{
			info =service.getWeatherInfo(currentCity, currentCountry);
		}catch(DataNotFoundException e){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Data not found!","No Weather Data found..."));
		}
					
		setWeatherInfo(info);
	}
	
	public List<String> loadCountries(String query){
		System.out.println(query);
		//return CountryUtil.getGlobalCountries(Locale.ENGLISH);	
		
		//return CountryUtil.getFirstLetterCountryFilter(query, Locale.ENGLISH);
		return CountryUtil.getFirstLetterFilter(query, Locale.ENGLISH);
	}

}
