package stone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * on 2017/7/31.
 */
public class Lexer extends ALog {
    private final LineNumberReader lineNumberReader;
    /**
     * //.* 注释
     * \d+ : 数字
     * "[^"]*" : 字符串
     * \w[\w\d]*|\p{Punct} : 变量|操作符
     */
    public static final Pattern TOKEN_REGEX = Pattern.compile("\\s*((//.*)|(\\d+)|(\"[^\"]*\")|(\\w[\\w\\d]*|\\p{Punct}))");


    private final List<Token> tokens;
    private boolean end;

    public Lexer(String path) throws Exception {
        this(new File(path));
    }

    public Lexer(File file) throws Exception {
        this.lineNumberReader = new LineNumberReader(new BufferedReader(new FileReader(file)));
        this.tokens = new LinkedList<>();
        this.end = false;
    }

    public Token read() {
        Token token = readOn(0);
        if (token != Token.END) {
            tokens.remove(0);
        }
        return token;
    }

    public Token peek(int pos) {
        return readOn(pos);
    }

    public Token readOn(int pos) {
        while (tokens.size() <= pos) {
            if (end) {
                return Token.END;
            }
            try {
                String line = lineNumberReader.readLine();
                //已经到末尾
                if (line == null) {
                    end = true;
                    continue;
                }
                int lineNum = lineNumberReader.getLineNumber();
                Matcher matcher = TOKEN_REGEX.matcher(line);
                while (matcher.find()) {
                    int column = matcher.start();
                    String comment = matcher.group(2);
                    if (comment != null) {
                        log.debug(lineNum + " " + column + " " + comment);
                        continue;
                    }
                    Token token;
                    String number = matcher.group(3);
                    if (number != null) {
                        token = new NumberToken(Long.valueOf(number), column, lineNum);
                    } else {
                        String str = matcher.group(4);
                        if (str != null) {
                            token = new StrToken(str, column, lineNum);
                        } else {
                            String id = matcher.group(5);
                            if (id != null) {
                                token = new IDToken(id, column, lineNum);
                            } else {
                                throw new ParseException("column[" + column + "],lineNum[" + lineNum + "]");
                            }
                        }
                    }
                    if (token.isNumber()) {
                        log.debug(lineNum + " " + column + " " + token.getNumber());
                    } else {
                        log.debug(lineNum + " " + column + " " + token.getText());
                    }
                    tokens.add(token);
                }

            } catch (Exception e) {
                throw new ParseException("", e);
            }

        }
        return tokens.get(pos);

    }

}
