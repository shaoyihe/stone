package stone;

import stone.ast.Symbols;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class BasicEnvironment implements Environment {
    private Map<String, Object> context = new HashMap<>();
    private Environment outer;

    private BasicEnvironment(Environment outer) {
        this.outer = outer;
    }

    private BasicEnvironment() {

    }

    @Override
    public Object get(String name) {
        Object v = context.get(name);
        if (v == null && outer != null)
            return outer.get(name);
        else
            return v;

    }

    @Override
    public void putNew(String key, Object value) {
        context.put(key, value);
    }

    @Override
    public void put(String key, Object value) {
        Environment environment = where(key);
        if (environment == null) {
            environment = this;
        }
        environment.putNew(key, value);
    }

    @Override
    public void setOuter(Environment environment) {
        this.outer = environment;
    }

    @Override
    public Environment where(String name) {
        if (context.containsKey(name)) {
            return this;
        }
        if (outer == null) {
            return null;
        }
        return outer.where(name);
    }

    @Override
    public Symbols symbols() {
        return null;
    }

    @Override
    public void put(int nest, int index, Object value) {

    }

    @Override
    public Object get(int nest, int index) {
        return null;
    }

    public static BasicEnvironment newEnv() {
        BasicEnvironment outer = new BasicEnvironment();
        Natives.registerNative(outer);
        return newEnv(outer);
    }

    public static BasicEnvironment newEnv(Environment environment) {
        return new BasicEnvironment(environment);
    }

}
