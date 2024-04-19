package design.patterns.behavioral.observer.subscriber;

import design.patterns.behavioral.observer.events.EventType;

public interface Listener {
    void update(EventType eventType, float newValue) throws Exception;

    String display();
}
