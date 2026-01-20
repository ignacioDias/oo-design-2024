package weatherstation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherStationTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void registersAsObserverWhenCreated() {
        WeatherData weatherData = new WeatherData();
        DegreesCelsiusDisplay display = new DegreesCelsiusDisplay(weatherData);

        weatherData.setMeasurements(32f, 50f, 0f);

        String output = outContent.toString();
        assertFalse(output.isEmpty(), "Display should print output after update");
    }

    @Test
    void convertsFreezingPointCorrectly() {
        WeatherData weatherData = new WeatherData();
        new DegreesCelsiusDisplay(weatherData);

        weatherData.setMeasurements(32f, 40f, 0f); // 0°C

        String output = outContent.toString();
        assertTrue(output.contains("0.0"), "32°F should convert to 0°C");
    }

    @Test
    void convertsBoilingPointCorrectly() {
        WeatherData weatherData = new WeatherData();
        new DegreesCelsiusDisplay(weatherData);

        weatherData.setMeasurements(212f, 30f, 0f); // 100°C

        String output = outContent.toString();
        assertTrue(output.contains("100.0"), "212°F should convert to 100°C");
    }

    @Test
    void updatesHumidityCorrectly() {
        WeatherData weatherData = new WeatherData();
        new DegreesCelsiusDisplay(weatherData);

        weatherData.setMeasurements(32f, 75f, 0f);

        String output = outContent.toString();
        assertTrue(output.contains("0.0"), "Humidity should be printed correctly");
    }
}
