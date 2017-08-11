package stone;

import org.junit.Test;
import stone.ast.ASTree;

/**
 * Created by heshaoyi on 8/4/17.
 */
public class ABTest {
    @Test
    public void test() throws Exception {
        Lexer lexer = new Lexer(LexTest.class.getResource("test.txt").getFile());
        BasicParser basicParser = new BasicParser();
        while (lexer.peek(0)!=Token.EOF){
            ASTree asTree = basicParser.parse(lexer);
            System.err.print(asTree);
        }
    }
}
