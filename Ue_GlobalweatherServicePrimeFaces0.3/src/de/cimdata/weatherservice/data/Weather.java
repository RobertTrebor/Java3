package de.cimdata.weatherservice.data;

public class Weather {
	
	private String temperature;
	private String relativeHumidity; // Luftfeuchtigkeit
	private String skyConditions; //z.B. bewölkt
	private String time;
	
	public Weather(){}
	public Weather(String temperature, String relativeHumidity,
			String skyConditions, String time) {
		super();
		this.temperature = temperature;
		this.relativeHumidity = relativeHumidity;
		this.skyConditions = skyConditions;
		this.time = time;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getRelativeHumidity() {
		return relativeHumidity;
	}
	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	public String getSkyConditions() {
		return skyConditions;
	}
	public void setSkyConditions(String skyConditions) {
		this.skyConditions = skyConditions;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Weather [temperature=" + temperature + ", relativeHumidity="
				+ relativeHumidity + ", skyConditions=" + skyConditions
				+ ", time=" + time + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((relativeHumidity == null) ? 0 : relativeHumidity.hashCode());
		result = prime * result
				+ ((skyConditions == null) ? 0 : skyConditions.hashCode());
		result = prime * result
				+ ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (relativeHumidity == null) {
			if (other.relativeHumidity != null)
				return false;
		} else if (!relativeHumidity.equals(other.relativeHumidity))
			return false;
		if (skyConditions == null) {
			if (other.skyConditions != null)
				return false;
		} else if (!skyConditions.equals(other.skyConditions))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	
	

}
