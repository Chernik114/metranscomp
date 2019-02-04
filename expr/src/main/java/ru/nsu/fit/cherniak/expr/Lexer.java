package ru.nsu.fit.cherniak.expr;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    private int cur;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        cur = reader.read();
    }

    public Lexeme nextLexeme() throws IOException, IllegalCharacterException {
        while (Character.isSpaceChar(cur)) { // Skip spaces
            cur = reader.read();
        }
        Lexeme l;
        switch (cur) {
            case '+':
                l = new Lexeme(LexemeType.PLUS, "+");
                cur = reader.read();
                break;
            case '-':
                l = new Lexeme(LexemeType.MINUS, "-");
                cur = reader.read();
                break;
            case '*':
                l = new Lexeme(LexemeType.MULT, "*");
                cur = reader.read();
                break;
            case '/':
                l = new Lexeme(LexemeType.DIV, "/");
                cur = reader.read();
                break;
            case '^':
                l = new Lexeme(LexemeType.POW, "^");
                cur = reader.read();
                break;
            case '(':
                l = new Lexeme(LexemeType.OPEN, "(");
                cur = reader.read();
                break;
            case ')':
                l = new Lexeme(LexemeType.CLOSE, ")");
                cur = reader.read();
                break;
            case -1:
                l = new Lexeme(LexemeType.EOF, "");
                cur = reader.read();
                break;
            default:
                if (!Character.isDigit(cur)) {
                    throw new IllegalCharacterException("Unknown character ", cur);
                }
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(cur)) {
                    sb.append((char)cur);
                    cur = reader.read();
                }
                l = new Lexeme(LexemeType.NUMBER, sb.toString());
                break;
        }
        return l;
    }
}
