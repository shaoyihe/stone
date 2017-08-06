package stone;

import stone.ast.ASTList;
import stone.ast.ASTree;
import stone.ast.BinaryExpr;

import java.util.List;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class LocalVarExpr extends ASTList {

    public LocalVarExpr(List<ASTree> children) {
        super(children);
    }

    public BinaryExpr assign() {
        if (!(child(0) instanceof BinaryExpr)) {
            throw new ParseException("require binary but got " + child(0));
        }
        return (BinaryExpr) child(0);
    }

    @Override
    public Object eval(Environment environment) {
        if (!"=".equals(assign().operator())) {
            throw new ParseException("require = but got " + assign().operator());
        }
        return assign().assign(environment, true);
    }
}
