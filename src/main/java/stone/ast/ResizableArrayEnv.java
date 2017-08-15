package stone.ast;

import stone.Environment;
import stone.Natives;

import java.util.Arrays;

/**
 * Created by heshaoyi on 8/14/17.
 */
public class ResizableArrayEnv extends ArrayEnv {
    protected Symbols names;

    private ResizableArrayEnv() {
        super(10, null);
        names = new Symbols();
    }

    @Override
    public Symbols symbols() {
        return names;
    }

    @Override
    public Object get(String name) {
        Integer i = names.find(name);
        if (i == null)
            if (outer == null)
                return null;
            else
                return outer.get(name);
        else
            return values[i];
    }

    @Override
    public void put(String name, Object value) {
        Environment e = where(name);
        if (e == null)
            e = this;
        ((ResizableArrayEnv) e).putNew(name, value);
    }

    @Override
    public void putNew(String name, Object value) {
        assign(names.putNew(name), value);
    }

    @Override
    public Environment where(String name) {
        if (names.find(name) != null)
            return this;
        else if (outer == null)
            return null;
        else
            return ((ResizableArrayEnv) outer).where(name);
    }

    @Override
    public void put(int nest, int index, Object value) {
        if (nest == 0)
            assign(index, value);
        else
            super.put(nest, index, value);
    }

    protected void assign(int index, Object value) {
        if (index >= values.length) {
            int newLen = values.length * 2;
            if (index >= newLen)
                newLen = index + 1;
            values = Arrays.copyOf(values, newLen);
        }
        values[index] = value;
    }

    public static ResizableArrayEnv newEnv(Environment environment) {
        ResizableArrayEnv resizableArrayEnv = new ResizableArrayEnv();
        resizableArrayEnv.setOuter(environment);
        return resizableArrayEnv;
    }

    public static ResizableArrayEnv newEnv() {
        ResizableArrayEnv resizableArrayEnv = new ResizableArrayEnv();
        Natives.registerNative(resizableArrayEnv);
        return resizableArrayEnv;
    }
}