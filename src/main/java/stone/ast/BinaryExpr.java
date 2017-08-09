package stone.ast;

import stone.Environment;
import stone.ParseException;

import java.util.List;

/**
 * 操作符表达式
 * （含左右节点和中间操作符节点）
 * Created by heshaoyi on 8/2/17.
 */
public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> children) {
        super(children);
    }

    public ASTree left() {
        return child(0);
    }

    public String operator() {
        return ((ASTLeaf) child(1)).getToken().getText();
    }

    public ASTree right() {
        return child(2);
    }

    @Override
    public Object eval(Environment environment) {
        String operator = operator();
        if ("=".equals(operator)) {
            return assign(environment, false);
        }

        Object left = left().eval(environment);
        Object right = right().eval(environment);
        if (left instanceof Long && right instanceof Long) {
            return compareInt((Long) left, (Long) right);
        }

        if ("+".equals(operator)) {
            return String.valueOf(left) + String.valueOf(right);
        }

        throw new ParseException("unkown operator " + operator);
    }

    private Object compareInt(Long left, Long right) {
        String operator = operator();
        if ("==".equals(operator)) {
            return left.equals(right);
        } else if (">".equals(operator)) {
            return left > right;
        } else if ("<".equals(operator)) {
            return left < right;
        } else if ("+".equals(operator)) {
            return left + right;
        } else if ("-".equals(operator)) {
            return left - right;
        } else if ("%".equals(operator)) {
            return left % right;
        } else if ("*".equals(operator)) {
            return left * right;
        } else if ("/".equals(operator)) {
            return left / right;
        }
        throw new ParseException("unkown operator " + operator);
    }

    /**
     * 赋值操作，只有当左值是标志符时有效
     *
     * @param environment
     * @return
     */
    public Object assign(Environment environment, boolean isLocal) {
        ASTree left = left();
        if (left instanceof Name) {
            Object right = right().eval(environment);
            String var = ((Name) left).name();
            if (isLocal) {
                environment.putNew(var, right);
            } else {
                environment.put(var, right);
            }
            return right;
        } else if (left instanceof PrimaryExpr) {
            if (left.child(left.numChildren() - 1) instanceof Dot) {
                return ((PrimaryExpr) left).evalAssign(environment, right().eval(environment));
            } else {
                throw new ParseException("illegal assign");
            }
        } else {
            throw new ParseException("require name but got " + left);
        }

    }
}