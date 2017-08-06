package stone;

import org.junit.Test;
import stone.ast.ASTree;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class ClosureTest {
    @Test
    public void test() throws Exception {
        Lexer lexer = new Lexer(LexTest.class.getResource("closure.txt").getFile());
        BasicParser basicParser = new ClosureParser();
        Environment environment = new BasicEnvironment();
        while (lexer.peek(0) != Token.EOF) {
            ASTree asTree = basicParser.parse(lexer);
            Object result = asTree.eval(environment);
            System.err.println("=> " + result);
        }

    }
}
