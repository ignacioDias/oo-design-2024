package weatherstation;

public class WeatherStation {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
	
		Observer currentDisplay = new CurrentConditionsDisplay(weatherData);
		Observer statisticsDisplay = new StatisticsDisplay(weatherData);
		Observer forecastDisplay = new ForecastDisplay(weatherData);
		weatherData.setMeasurements(80, 65, 30.4f);

		weatherData.removeObserver(forecastDisplay);
		weatherData.setMeasurements(62, 90, 28.1f);
	}
}
