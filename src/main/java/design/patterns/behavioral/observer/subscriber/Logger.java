package design.patterns.behavioral.observer.subscriber;

import design.patterns.behavioral.observer.events.EventType;

public class Logger implements Listener {
    private static float temperature;
    private static float humidity;
    private static float pressure;
    private static float windSpeed;

    @Override
    public void update(EventType eventType, float newValue) throws Exception {
        if (eventType.equals(EventType.TEMPERATURE)) {
            temperature = newValue;
        } else if (eventType.equals(EventType.HUMIDITY)) {
            humidity = newValue;
        } else if (eventType.equals(EventType.PRESSURE)) {
            pressure = newValue;
        } else if (eventType.equals(EventType.WIND_SPEED)) {
            windSpeed = newValue;
        } else {
            throw new Exception("Invalid event type");
        }
    }

    public String display() {
        return "Temperature: "
                + temperature
                + " Humidity: "
                + humidity
                + " Pressure: "
                + pressure
                + " Wind Speed: "
                + windSpeed;
    }
}
