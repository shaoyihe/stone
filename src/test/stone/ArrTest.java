package stone;

import org.junit.Test;
import stone.ast.ASTree;
import stone.ast.NullStmnt;

/**
 * Created by heshaoyi on 8/10/17.
 */
public class ArrTest {
    @Test
    public void test() throws Exception {
        Lexer lexer = new Lexer(LexTest.class.getResource("arr.txt").getFile());
        BasicParser basicParser = new ArrParser();
        Environment environment = BasicEnvironment.newEnv();
        while (lexer.peek(0) != Token.EOF) {
            ASTree asTree = basicParser.parse(lexer);
            if (!(asTree instanceof NullStmnt)) {
                Object result = asTree.eval(environment);
                System.err.println("=> " + result);
            }
        }
    }
}
