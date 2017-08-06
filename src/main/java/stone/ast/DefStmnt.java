package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class DefStmnt extends ASTList {
    public DefStmnt(List<ASTree> children) {
        super(children);
    }

    public String operator() {
        return ((ASTLeaf) child(0)).getToken().getText();
    }

    public Parameters params() {
        return (Parameters) ((ASTree) child(1)).child(0);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(2);
    }

    @Override
    public Object eval(Environment environment) {
        environment.putNew(operator(), new Func(params(), body(), environment));
        return operator();
    }
}
