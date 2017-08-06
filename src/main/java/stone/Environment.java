package stone;

/**
 * Created by heshaoyi on 8/6/17.
 */
public interface Environment {
    Object get(String key);

    void putNew(String key, Object value);

    void put(String key, Object value);

    void setOuter(Environment environment);

    Environment where(String name);
}
