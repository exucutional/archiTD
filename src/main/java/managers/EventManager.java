package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import events.EventListener;
import events.EventType;

public class EventManager {

    private Map<EventType, List<EventListener>> listenerMap = new HashMap<>();

    public EventManager() {
        for (EventType eventType : EventType.values()) {
            listenerMap.put(eventType, new ArrayList<>());
        }
    }

    public void publish(EventType eventType) {
        List<EventListener> listeners = listenerMap.get(eventType);
        for (EventListener listener : listeners) {
            listener.update();
        }
    }

    public void subscribe(EventType eventType, EventListener eventListener) {
        List<EventListener> listeners = listenerMap.get(eventType);
        listeners.add(eventListener);
    }

    public void unsubcribe(EventType eventType, EventListener eventListener) {
        List<EventListener> listeners = listenerMap.get(eventType);
        listeners.remove(eventListener);
    }

    public void unsubcribeAll(EventType eventType) {
        List<EventListener> listeners = listenerMap.get(eventType);
        listeners.clear();
    }

}
