package stone.ast;

import stone.Environment;

/**
 * Created by heshaoyi on 8/9/17.
 */
public class StoneObject {
    public static class AccessException extends Exception {
    }

    protected Environment env;

    public StoneObject(Environment e) {
        env = e;
    }

    @Override
    public String toString() {
        return "<object:" + hashCode() + ">";
    }

    public Object read(String member) {
        return getEnv(member).get(member);
    }

    public Object write(String member, Object value) {
        getEnv(member).putNew(member, value);
        return value;
    }

    private Environment getEnv(String member) {
        Environment e = env.where(member);
        if (e != null && e == env)
            return e;
        else
            throw new RuntimeException(member + " not exist in " + this);
    }
}
