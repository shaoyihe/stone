package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * Created by heshaoyi on 8/10/17.
 */
public class ArrayRef extends ASTList {
    public ArrayRef(List<ASTree> children) {
        super(children);
    }

    @Override
    public Object eval(Environment environment) {
        return child(0).eval(environment);
    }
}
