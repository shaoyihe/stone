package stone.ast;

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
}
