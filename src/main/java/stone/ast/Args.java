package stone.ast;

import stone.Environment;
import stone.ParseException;

import java.util.List;

/**
 * 实参
 * Created by heshaoyi on 8/6/17.
 */
public class Args extends ASTList {
    public Args(List<ASTree> children) {
        super(children);
    }

    public Object eval(Environment callEnv, Object func) {
        if (func instanceof NativeFunc) {
            NativeFunc nativeFunc = (NativeFunc) func;
            if (numChildren() != ((NativeFunc) func).getMethod().getParameterCount()) {
                throw new ParseException("size not equal");
            }
            Object[] args = new Object[numChildren()];
            for (int i = 0; i < numChildren(); ++i) {
                args[i] = child(i).eval(callEnv);
            }
            try {
                return nativeFunc.getMethod().invoke(null, args);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ParseException(e.getMessage());
            }
        }


        if (!(func instanceof OptFunction)) {
            throw new ParseException("require OptFunction but got " + func);
        }

        Func f = (OptFunction) func;
        if (f.getParameters().size() != numChildren()) {
            throw new ParseException("size not equal");
        }
        Environment newEnv = f.makeEnv();
        for (int i = 0; i < numChildren(); ++i) {
            f.getParameters().eval(newEnv, i, child(i).eval(callEnv));
        }
        return ((Func) func).getBody().eval(newEnv);
    }
}
