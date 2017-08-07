package stone;

import stone.ast.NativeFunc;

/**
 * Created by heshaoyi on 8/7/17.
 */
public class Natives {

    public static void registerNative(Environment environment) {
        try {
            environment.putNew("currentTime", new NativeFunc(Natives.class.getMethod("currentTime")));
            environment.putNew("print", new NativeFunc(Natives.class.getMethod("print", Object.class)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public static long currentTime() {
        return System.currentTimeMillis();
    }

    public static void print(Object arg) {
        System.out.println(arg);
    }
}
