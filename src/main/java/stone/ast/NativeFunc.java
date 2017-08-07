package stone.ast;

import java.lang.reflect.Method;

/**
 * Created by heshaoyi on 8/7/17.
 */
public class NativeFunc {
    private Method method;

    public NativeFunc(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }
}
