package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * Created by heshaoyi on 8/9/17.
 */
public class ClassBody extends ASTList {
    public ClassBody(List<ASTree> children) {
        super(children);
    }

    @Override
    public Object eval(Environment environment) {
        for (ASTree tree : this) {
            tree.eval(environment);
        }
        return null;
    }
}
