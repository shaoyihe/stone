package stone;

import org.junit.Test;

/**
 * Created by heshaoyi on 7/31/17.
 */
public class LexTest {

    @Test
    public void tokenReadr() throws Exception {
        Lexer lexer = new Lexer((LexTest.class.getResource("test.txt").getFile()));
        Token token;
        while ((token=lexer.read()) !=Token.EOF){
            System.err.println(token);
        }
    }
}
