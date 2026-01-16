package weatherstation;

public class FeelsLikeTemperatureDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public FeelsLikeTemperatureDisplay() { };

    public FeelsLikeTemperatureDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.display();
    }
    @Override
    public void display() {
        float humidityPercent = this.humidity * 100;
        float heatIndex = computeHeatIndex(this.temperature, humidityPercent);
        System.out.println("Feels like temperature: " + heatIndex);
    }
    private float computeHeatIndex(float airTemperature, float relativeHumidity) {
        float index = (float)((16.923 + (0.185212 * airTemperature) + (5.37941 * relativeHumidity) - (0.100254 * airTemperature * relativeHumidity)
                + (0.00941695 * (airTemperature * airTemperature)) + (0.00728898 * (relativeHumidity * relativeHumidity))
                + (0.000345372 * (airTemperature * airTemperature * relativeHumidity)) - (0.000814971 * (airTemperature * relativeHumidity * relativeHumidity)) +
                (0.0000102102 * (airTemperature * airTemperature * relativeHumidity * relativeHumidity)) - (0.000038646 * (airTemperature * airTemperature * airTemperature)) + (0.0000291583 *
                (relativeHumidity * relativeHumidity * relativeHumidity)) + (0.00000142721 * (airTemperature * airTemperature * airTemperature * relativeHumidity)) +
                (0.000000197483 * (airTemperature * relativeHumidity * relativeHumidity * relativeHumidity)) - (0.0000000218429 * (airTemperature * airTemperature * airTemperature * relativeHumidity * relativeHumidity)) +
                0.000000000843296 * (airTemperature * airTemperature * relativeHumidity * relativeHumidity * relativeHumidity)) -
                (0.0000000000481975 * (airTemperature * airTemperature * airTemperature * relativeHumidity * relativeHumidity * relativeHumidity)));
        return index;
    }
}
