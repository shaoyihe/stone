package stone.ast;

import java.util.List;

/**
 * .操作
 * Created by heshaoyi on 8/9/17.
 */
public class Dot extends ASTList {
    public Dot(List<ASTree> children) {
        super(children);
    }

    public String name() {
        return ((ASTLeaf) child(0)).text();
    }
}
