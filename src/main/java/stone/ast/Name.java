package stone.ast;

import stone.Token;

/**
 * Created by heshaoyi on 8/2/17.
 */
public class Name extends ASTLeaf {
    public Name(Token token) {
        super(token);
    }

    public String name() {
        return getToken().getText();
    }
}
