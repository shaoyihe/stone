package stone.ast;

import stone.Environment;

import java.util.List;

public class BlockStmnt extends ASTList {
    public BlockStmnt(List<ASTree> c) {
        super(c);
    }

    @Override
    public Object eval(Environment environment) {
        Object result = null;
        for (ASTree asTree : this) {
            result = asTree.eval(environment);
        }
        return result;
    }
}
