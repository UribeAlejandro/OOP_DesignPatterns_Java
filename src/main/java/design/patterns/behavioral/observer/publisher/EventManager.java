package design.patterns.behavioral.observer.publisher;

import design.patterns.behavioral.observer.events.EventType;
import design.patterns.behavioral.observer.subscriber.Listener;
import java.util.ArrayList;
import java.util.HashMap;

public class EventManager implements Publisher {
    private static HashMap<EventType, ArrayList<Listener>> listeners = new HashMap<>();

    @Override
    public void subscribe(EventType eventType, Listener listener) {
        ArrayList<Listener> event_listeners;
        if (listeners.containsKey(eventType)) {
            event_listeners = listeners.get(eventType);
        } else {
            event_listeners = new ArrayList<>();
        }
        event_listeners.add(listener);
        listeners.put(eventType, event_listeners);
    }

    @Override
    public void notify(EventType eventType, float newValue) throws Exception {
        ArrayList<Listener> listeners_notified = listeners.get(eventType);
        for (Listener listener : listeners_notified) {
            listener.update(eventType, newValue);
        }
    }

    public ArrayList<Listener> get_listeners(EventType eventType) {
        return listeners.get(eventType);
    }
}
