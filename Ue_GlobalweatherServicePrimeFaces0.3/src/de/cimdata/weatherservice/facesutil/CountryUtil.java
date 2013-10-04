package de.cimdata.weatherservice.facesutil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CountryUtil {
	
	public static List<String> getGlobalCountries(Locale locale){
		List<String> countries = new ArrayList<>();
		String[] locales =  Locale.getISOCountries();
		
		for (String countryCode : locales) {
			Locale obj = new Locale("",countryCode);
			countries.add(obj.getDisplayCountry(locale));
		}
		
		
		return countries;
	}
	
	//----------------------- Filter -------------------------------------------------
	public static List<String> getFirstLetterFilter(String query, Locale locale) {
		List<String> liste = CountryUtil.getGlobalCountries(locale);
		List<String> countries = new ArrayList<>();
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).toLowerCase().startsWith(query.toLowerCase())) {
				countries.add(liste.get(i));
			}
		}
		return countries;
	}
	
	public static List<String> getFirstLetterCountryFilter(String query, Locale locale) {
		List<String> countries = getGlobalCountries(locale);
		
		for (Iterator<String> it=countries.iterator(); it.hasNext();) {
		    if (!it.next().contains(query))
		        it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
		}
		return countries;
	}
	public static void main(String[] args) {
		System.out.println(getGlobalCountries(Locale.GERMAN));
	}

}
