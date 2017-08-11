package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * 定义数组
 * Created by heshaoyi on 8/10/17.
 */
public class ArrayLiteral extends ASTList {
    public ArrayLiteral(List<ASTree> children) {
        super(children);
    }

    @Override
    public Object eval(Environment environment) {
        Object[] arr = new Object[numChildren()];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = child(i).eval(environment);
        }
        return arr;
    }
}
