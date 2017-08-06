package stone.ast;

import stone.Environment;
import stone.ParseException;

import java.util.List;

public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> c) {
        super(c);
    }

    public ASTree operand() {
        return child(0);
    }

    public String toString() {
        return "-" + operand();
    }

    @Override
    public Object eval(Environment environment) {
        //获取右值
        Object operand = operand().eval(environment);
        if (operand instanceof Integer) {
            return -(Integer) operand;
        } else {
            throw new ParseException("required number but got " + operand);
        }
    }
}
