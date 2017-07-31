package stone;

import java.io.*;

/**
 * on 2017/7/31.
 */
public class Lexer {
    private final LineNumberReader lineNumberReader;

    public Lexer(File file) throws Exception {
        this.lineNumberReader = new LineNumberReader(new BufferedReader(new FileReader(file)));
    }
}
