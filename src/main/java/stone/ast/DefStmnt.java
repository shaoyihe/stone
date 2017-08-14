package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class DefStmnt extends ASTList {
    private int index;
    private int size;

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
    public Object eval(Environment env) {
        ((ResizableArrayEnv) env).put(0, index, new OptFunction(params(), body(), env, size));
        return name();
    }

    @Override
    public void lookup(Symbols syms) {
        index = syms.putNew(name());
        size = Fun.lookup(syms, params(), body());
    }
}
