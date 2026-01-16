package weatherstation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherStationTest {
    private WeatherData weatherData;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        weatherData = new WeatherData();
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void observersAreNotifiedWhenMeasurementsChange() {
        new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);

        String output = out.toString();
        assertTrue(output.contains("80.0F degrees"));
        assertTrue(output.contains("65.0% humidity"));
    }

    @Test
    void removedObserverIsNotNotified() {
        CurrentConditionsDisplay display =
                new CurrentConditionsDisplay(weatherData);

        weatherData.removeObserver(display);
        weatherData.setMeasurements(70, 50, 29.2f);

        assertEquals("", out.toString());
    }
    @Test
    void displaysCurrentTemperatureAndHumidity() {
        new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(75, 60, 30);

        String output = out.toString();
        assertTrue(output.contains("Current conditions"));
        assertTrue(output.contains("75.0F degrees"));
        assertTrue(output.contains("60.0% humidity"));
    }
    @Test
    void convertsFahrenheitToCelsiusCorrectly() {
        new DegreesCelsiusDisplay(weatherData);

        weatherData.setMeasurements(32, 0, 0);

        String output = out.toString();
        assertTrue(output.contains("0.0C° degrees"));
    }

    @Test
    void convertsAnotherValueCorrectly() {
        new DegreesCelsiusDisplay(weatherData);

        weatherData.setMeasurements(68, 0, 0);

        String output = out.toString();
        assertTrue(output.contains("20.0C° degrees"));
    }
    @Test
    void calculatesAverageMaxAndMinTemperature() {
        new StatisticsDisplay(weatherData);

        weatherData.setMeasurements(80, 0, 0);
        weatherData.setMeasurements(70, 0, 0);
        weatherData.setMeasurements(90, 0, 0);

        String output = out.toString();
        assertTrue(output.contains("Avg/Max/Min temperature"));
        assertTrue(output.contains("80.0/90.0/70.0"));
    }
}
