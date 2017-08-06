package stone;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class BasicEnvironment implements Environment {
    private Map<String, Object> context = new HashMap<>();

    @Override
    public Object get(String key) {
        return context.get(key);
    }

    @Override
    public void put(String key, Object value) {
        context.put(key, value);
    }
}
