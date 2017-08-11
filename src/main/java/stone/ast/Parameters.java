package stone.ast;

import java.util.List;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class Parameters extends ASTList {
    public Parameters(List<ASTree> children) {
        super(children);
    }

    public String name(int index) {
        return ((ASTLeaf) child(index)).getToken().getText();
    }

    public int size() {
        return numChildren();
    }
}
