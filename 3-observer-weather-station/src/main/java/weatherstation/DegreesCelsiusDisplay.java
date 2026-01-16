package weatherstation;

public class DegreesCelsiusDisplay implements Observer, DisplayElement {
    private float temperature;
    private WeatherData weatherData;

    public DegreesCelsiusDisplay() { };

    public DegreesCelsiusDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        display();
    }

    public void display() {
        float celsiusTemperature = convertFromFahrenheitToCelsius();
        System.out.println("Current temperature: " + celsiusTemperature
                + "C° degrees");
    }
    private float convertFromFahrenheitToCelsius() {
        return ((this.temperature - 32) * 5) / 9;
    }
}
