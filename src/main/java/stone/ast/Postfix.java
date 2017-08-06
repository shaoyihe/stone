package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class Postfix extends ASTList {
    public Postfix(List<ASTree> children) {
        super(children);
    }

    public Object eval(Environment callEnv, Object func) {
        return ((Args) child(0)).eval(callEnv, func);
    }
}
