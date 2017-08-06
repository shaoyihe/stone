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
        if (!(func instanceof Func)) {
            throw new ParseException("require fun but got " + func);
        }

        Func f = (Func) func;
        if (f.getParameters().size() != numChildren()) {
            throw new ParseException("size not equal");
        }
        Environment newEnv = f.makeEnv();
        for (int i = 0; i < numChildren(); ++i) {
            newEnv.putNew(f.getParameters().name(i), child(i).eval(callEnv));
        }
        return ((Func) func).getBody().eval(newEnv);
    }
}
