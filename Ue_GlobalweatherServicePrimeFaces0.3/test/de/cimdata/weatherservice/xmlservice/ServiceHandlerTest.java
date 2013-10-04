package de.cimdata.weatherservice.xmlservice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.cimdata.weatherservice.data.WeatherInfo;
import de.cimdata.weatherservice.exception.DataNotFoundException;

public class ServiceHandlerTest {
	private ServiceHandler service;
	@Before
	public void setUp() throws Exception {
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "10.101.21.1");
		System.setProperty("http.proxyPort", "8877");
		service = new ServiceHandler();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test(timeout=40000)
	public void testGetCityNames() {
		List<String> l1 = service.getCityNames("Germany");
		assertNotNull(l1);
		assertTrue(l1.size()>0);
		List<String> l2 = service.getCityNames("xyz");
		assertTrue(l2.size()==0);
		List<String> l3 = service.getCityNames("");
		assertTrue(l3.size()>10);

		List<String> l4 = service.getCityNames("Germany");
		boolean berlin = false;
		for (String stadt : l4) {
			if(stadt.equalsIgnoreCase("Berlin-Schoenefeld")){
				berlin = true;
			}
		}
		assertTrue(berlin);
	}

	@Test
	public void testGetWeatherInfo() {
		WeatherInfo wi = service.getWeatherInfo("Berlin", "Germany");
		assertNotNull(wi);
		WeatherInfo wi2 = service.getWeatherInfo("Berlin", "Germany");
	}
	
	@Test(expected=DataNotFoundException.class)
	public void testGetWeatherInfoException(){
		WeatherInfo wi = service.getWeatherInfo("Pferdsfeld", "Germany");
	}
	

}
