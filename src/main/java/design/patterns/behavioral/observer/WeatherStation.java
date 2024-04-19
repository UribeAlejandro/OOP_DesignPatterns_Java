package design.patterns.behavioral.observer;

import design.patterns.behavioral.observer.events.EventType;
import design.patterns.behavioral.observer.publisher.EventManager;

public class WeatherStation {
    private static float temperature;
    private static float humidity;
    private static float pressure;
    private static float windSpeed;
    private static EventManager eventManager = new EventManager();

    public static void setTemperature(float temperature) throws Exception {
        WeatherStation.temperature = temperature;
        eventManager.notify(EventType.TEMPERATURE, temperature);
    }

    public static void setHumidity(float humidity) throws Exception {
        WeatherStation.humidity = humidity;
        eventManager.notify(EventType.HUMIDITY, humidity);
    }

    public static void setPressure(float pressure) throws Exception {
        WeatherStation.pressure = pressure;
        eventManager.notify(EventType.PRESSURE, pressure);
    }

    public static void setWindSpeed(float windSpeed) throws Exception {
        WeatherStation.windSpeed = windSpeed;
        eventManager.notify(EventType.WIND_SPEED, windSpeed);
    }
}
