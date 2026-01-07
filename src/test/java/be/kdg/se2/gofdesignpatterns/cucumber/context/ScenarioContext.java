package be.kdg.se2.gofdesignpatterns.cucumber.context;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ScenarioScope
public class ScenarioContext {
    private final Map<String, Object> context = new HashMap<>();

    public void setContext(String key, Object value) {
        context.put(key, value);
    }

    public Object getContext(String key) {
        return context.get(key);
    }

    public <T> T getContext(String key, Class<T> clazz) {
        Object value = context.get(key);
        if (value == null) return null;
        return clazz.cast(value);
    }

    public boolean contains(String key) {
        return context.containsKey(key);
    }

    public void remove(String key) {
        context.remove(key);
    }

    public void clear() {
        context.clear();
    }
}