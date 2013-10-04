package de.cimdata.weatherservice.xmlservice;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.cimdata.weatherservice.client.GlobalWeather;
import de.cimdata.weatherservice.client.GlobalWeatherSoap;
import de.cimdata.weatherservice.data.Weather;
import de.cimdata.weatherservice.data.WeatherInfo;
import de.cimdata.weatherservice.exception.DataNotFoundException;
import de.cimdata.weatherservice.exception.WetterWebServiceException;

public class ServiceHandler {

	public ServiceHandler() {
		System.setProperty("java.net.useSystemProxies", "true");

		// System.setProperty("http.proxySet", "true");
		// System.setProperty("http.proxyHost", "10.101.21.1");
		// System.setProperty("http.proxyPort", "8877");
	}

	private GlobalWeatherSoap getSoap() {
		GlobalWeather gw = new GlobalWeather();
		GlobalWeatherSoap gws = (GlobalWeatherSoap) gw.getGlobalWeatherSoap();
		return gws;
	}

	public List<String> getCityNames(String countryName) {
		String xml = "";
		try {
			GlobalWeatherSoap gws = getSoap();
			xml = gws.getCitiesByCountry(countryName);
		} catch (WebServiceException e) {
			throw new WetterWebServiceException();
		}
		return createCityList(xml);
	}

	private Element getRoot(String xml) {
		SAXBuilder b = new SAXBuilder();
		Document doc = null;
		try {
			doc = b.build(new StringReader(xml));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc.getRootElement();
	}

	private List<String> createCityList(String xml) {
		if (xml.equals("")) {
			throw new IllegalArgumentException();
		}
		List<String> cityNames = new ArrayList<>();
		Element newDataSet = getRoot(xml);
		List<Element> elements = newDataSet.getChildren("Table");
		for (Element element : elements) {
			cityNames.add(element.getChildTextTrim("City"));
		}
		Collections.sort(cityNames);
		return cityNames;
	}

	/**
	 * Liefert eine WeatherInfo für ein bestimmtes Land
	 * 
	 * @param country
	 * @return
	 */
	public WeatherInfo getWeatherInfo(String cityName, String countryName) {
		GlobalWeatherSoap gws = getSoap();
		String xml = gws.getWeather(cityName, countryName);
		if (xml.equals("Data Not Found")) {
			throw new DataNotFoundException();
		}
		return createWeatherInfo(cityName, xml);
	}

	private WeatherInfo createWeatherInfo(String cityName, String xml) {
		WeatherInfo weatherInfo = null;
		Element currentWeather = getRoot(xml);
		String temperature = currentWeather.getChildText("Temperature");
		String time = currentWeather.getChildText("Time");
		String relativeHumidity = currentWeather
				.getChildText("RelativeHumidity");
		String skyConditions = currentWeather.getChildText("SkyConditions");
		Weather weather = new Weather(temperature, relativeHumidity,
				skyConditions, time);
		weatherInfo = new WeatherInfo(cityName, weather);
		return weatherInfo;
	}

	public static void main(String[] args) {
		// new SeviceHandler().getCityNames("Germany");

		ServiceHandler s = new ServiceHandler();
		s.getCityNames("Germany");
		// System.out.println(s.getWeatherInfo("Berlin", "Germany"));
	}

}
