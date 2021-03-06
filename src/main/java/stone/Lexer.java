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
    public static final Pattern TOKEN_REGEX = Pattern.compile("\\s*((//.*)|(\\d+)|(\"[^\"]*\")|(\\w[\\w\\d]*|==|&&|\\|\\||\\p{Punct}))");


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
        if (token != Token.EOF) {
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
                return Token.EOF;
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
                    String comment = matcher.group(2);
                    if (comment != null) {
                        log.debug(lineNum + " " + matcher.start() + " " + comment);
                        continue;
                    }
                    Token token;
                    String number = matcher.group(3);
                    if (number != null) {
                        token = new NumberToken(Long.valueOf(number), matcher.start(), lineNum);
                    } else {
                        String str = matcher.group(4);
                        if (str != null) {
                            token = new StrToken(str, matcher.start(), lineNum);
                        } else {
                            String id = matcher.group(5);
                            if (id != null) {
                                token = new IDToken(id, matcher.start(), lineNum);
                            } else {
                                throw new ParseException("error");
                            }
                        }
                    }
                    if (token.isNumber()) {
                        log.debug(lineNum + " " + matcher.start() + " " + token.getNumber());
                    } else {
                        log.debug(lineNum + " " + matcher.start() + " " + token.getText());
                    }
                    tokens.add(token);
                }
                tokens.add(new IDToken(Token.EOL, line.length(), lineNum));

            } catch (Exception e) {
                throw new ParseException(e.toString());
            }

        }
        return tokens.get(pos);

    }

}
