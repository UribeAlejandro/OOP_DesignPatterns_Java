package design.patterns.behavioral.observer.publisher;

import static org.junit.jupiter.api.Assertions.*;

import design.patterns.behavioral.observer.events.EventType;
import design.patterns.behavioral.observer.subscriber.AlertSystem;
import design.patterns.behavioral.observer.subscriber.Listener;
import design.patterns.behavioral.observer.subscriber.Logger;
import design.patterns.behavioral.observer.subscriber.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventManagerTest {
    static EventManager eventManager;
    static User user;
    static Logger logger;
    static AlertSystem alertSystem;
    static ArrayList<Listener> temperatureListeners;
    static ArrayList<Listener> windSpeedListeners;
    static ArrayList<Listener> humidityListeners;
    static ArrayList<Listener> pressureListeners;
    static final float temperature = 25.0f;
    static final float windSpeed = 10.0f;
    static final float humidity = 50.0f;
    static final float pressure = 1000.0f;

    @BeforeAll
    static void initAll() throws Exception {
        eventManager = new EventManager();
        user = new User();
        logger = new Logger();
        alertSystem = new AlertSystem();

        temperatureListeners = new ArrayList<>();
        temperatureListeners.add(user);
        temperatureListeners.add(logger);
        temperatureListeners.add(alertSystem);

        windSpeedListeners = new ArrayList<>();
        windSpeedListeners.add(logger);
        windSpeedListeners.add(alertSystem);

        humidityListeners = new ArrayList<>();
        humidityListeners.add(user);
        humidityListeners.add(logger);

        pressureListeners = new ArrayList<>();
        pressureListeners.add(logger);

        HashMap<EventType, ArrayList<Listener>> listeners = new HashMap<>();
        listeners.put(EventType.TEMPERATURE, temperatureListeners);
        listeners.put(EventType.WIND_SPEED, windSpeedListeners);
        listeners.put(EventType.HUMIDITY, humidityListeners);
        listeners.put(EventType.PRESSURE, pressureListeners);

        for (Map.Entry<EventType, ArrayList<Listener>> set : listeners.entrySet()) {
            EventType eventType = set.getKey();
            ArrayList<Listener> listenerList = set.getValue();
            for (Listener listener : listenerList) {
                eventManager.subscribe(eventType, listener);
            }
        }

        eventManager.notify(EventType.TEMPERATURE, temperature);
        eventManager.notify(EventType.WIND_SPEED, windSpeed);
        eventManager.notify(EventType.HUMIDITY, humidity);
        eventManager.notify(EventType.PRESSURE, pressure);
    }

    private static Stream<Arguments> subscribeArguments() {
        return Stream.of(
                Arguments.of(EventType.TEMPERATURE, temperatureListeners.size()),
                Arguments.of(EventType.WIND_SPEED, windSpeedListeners.size()),
                Arguments.of(EventType.HUMIDITY, humidityListeners.size()),
                Arguments.of(EventType.PRESSURE, pressureListeners.size()));
    }

    private static Stream<Arguments> notifyArguments() {
        return Stream.of(
                Arguments.of(user, "Temperature: " + temperature + " Humidity: " + humidity),
                Arguments.of(
                        logger,
                        "Temperature: "
                                + temperature
                                + " Humidity: "
                                + humidity
                                + " Pressure: "
                                + pressure
                                + " Wind Speed: "
                                + windSpeed),
                Arguments.of(
                        alertSystem, "[Alert] Temperature: " + temperature + " Wind Speed: " + windSpeed));
    }

    @ParameterizedTest
    @MethodSource("subscribeArguments")
    @DisplayName("Test Subscribe")
    void subscribe(EventType eventType, int numListener) {
        assertEquals(numListener, eventManager.get_listeners(eventType).size());
    }

    @ParameterizedTest
    @MethodSource("notifyArguments")
    @DisplayName("Test Notify")
    void notify(Listener listener, String notification) {
        assertEquals(notification, listener.display());
    }
}
