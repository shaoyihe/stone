package stone;

import org.junit.Test;
import stone.ast.ASTree;
import stone.ast.NullStmnt;
import stone.ast.ResizableArrayEnv;

/**
 * Created by heshaoyi on 8/10/17.
 */
public class OptTest {

    @Test
    public void test() throws Exception {
        Lexer lexer = new Lexer(LexTest.class.getResource("native.txt").getFile());
        BasicParser basicParser = new ClassParser();
        ResizableArrayEnv environment = ResizableArrayEnv.newEnv();
        while (lexer.peek(0) != Token.EOF) {
            ASTree asTree = basicParser.parse(lexer);
            if (!(asTree instanceof NullStmnt)) {
                asTree.lookup(environment.symbols());
                Object result = asTree.eval(environment);
                System.err.println("=> " + result);
            }
        }
    }
}
