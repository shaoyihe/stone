package stone;

import stone.ast.Symbols;

/**
 * Created by heshaoyi on 8/6/17.
 */
public interface Environment {
    Object get(String key);

    /**
     * 放到当前位置
     *
     * @param key
     * @param value
     */
    void putNew(String key, Object value);

    void put(String key, Object value);

    void setOuter(Environment environment);

    Environment where(String name);

    Symbols symbols();

    void put(int nest, int index, Object value);

    Object get(int nest, int index);
}
