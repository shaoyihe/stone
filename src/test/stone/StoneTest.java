package stone;

import org.junit.Test;

/**
 * Created by heshaoyi on 7/31/17.
 */
public class StoneTest {

    @Test
    public void tokenReadr() throws Exception {
        Lexer lexer = new Lexer((StoneTest.class.getResource("test.txt").getFile()));
        Token token;
        while ((token=lexer.read()) !=Token.END){
            System.err.println(token);
        }
    }
}
