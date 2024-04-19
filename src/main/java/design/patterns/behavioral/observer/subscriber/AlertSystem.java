package design.patterns.behavioral.observer.subscriber;

import design.patterns.behavioral.observer.events.EventType;

public class AlertSystem implements Listener {
    private static float temperature;
    private static float windSpeed;

    @Override
    public void update(EventType eventType, float newValue) throws Exception {
        if (eventType.equals(EventType.TEMPERATURE)) {
            temperature = newValue;
        } else if (eventType.equals(EventType.WIND_SPEED)) {
            windSpeed = newValue;
        } else {
            throw new Exception("Invalid event type");
        }
    }

    public String display() {
        return "[Alert] Temperature: " + temperature + " Wind Speed: " + windSpeed;
    }
}
