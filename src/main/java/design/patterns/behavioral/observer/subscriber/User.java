package design.patterns.behavioral.observer.subscriber;

import design.patterns.behavioral.observer.events.EventType;

public class User implements Listener {
    private static float temperature;
    private static float humidity;

    @Override
    public void update(EventType eventType, float newValue) throws Exception {
        if (eventType.equals(EventType.TEMPERATURE)) {
            temperature = newValue;
        } else if (eventType.equals(EventType.HUMIDITY)) {
            humidity = newValue;
        } else {
            throw new Exception("Invalid event type");
        }
    }

    public String display() {
        return "Temperature: " + temperature + " Humidity: " + humidity;
    }
}
