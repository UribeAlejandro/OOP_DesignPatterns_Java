package design.patterns.behavioral.observer.publisher;

import design.patterns.behavioral.observer.events.EventType;
import design.patterns.behavioral.observer.subscriber.Listener;

public interface Publisher {
    void subscribe(EventType eventType, Listener listener);

    void notify(EventType eventType, float newValue) throws Exception;
}
