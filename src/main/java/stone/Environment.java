package stone;

/**
 * Created by heshaoyi on 8/6/17.
 */
public interface Environment {
    Object get(String key);

    void put(String key, Object value);
}
