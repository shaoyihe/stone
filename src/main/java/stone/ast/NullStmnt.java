package stone.ast;

import stone.Environment;

import java.util.List;

public class NullStmnt extends ASTList {
    public NullStmnt(List<ASTree> c) {
        super(c);
    }

    @Override
    public Object eval(Environment environment) {
        return null;
    }
}
