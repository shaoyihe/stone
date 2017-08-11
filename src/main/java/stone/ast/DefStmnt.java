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

    public String name() {
        return ((ASTLeaf) child(0)).getToken().getText();
    }

    public Parameters params() {
        return (Parameters) child(1);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(2);
    }

    @Override
    public Object eval(Environment environment) {
        environment.putNew(name(), new Func(params(), body(), environment));
        return name();
    }
}
