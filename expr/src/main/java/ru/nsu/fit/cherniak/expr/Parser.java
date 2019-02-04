package ru.nsu.fit.cherniak.expr;

import java.io.IOException;

public class Parser {
    private Lexer lexer;
    private Lexeme cur;

    public Parser(Lexer lexer) throws IOException, IllegalCharacterException {
        this.lexer = lexer;
        this.cur = lexer.nextLexeme();
    }

    public long parseExpr() throws IOException, IllegalCharacterException, IllegalLexemeException {
        long temp = parseTerm();
        while (cur.type == LexemeType.PLUS || cur.type == LexemeType.MINUS) {
            LexemeType type = cur.type;
            cur = lexer.nextLexeme();
            if (type == LexemeType.PLUS) {
                temp += parseTerm();
            } else {
                temp -= parseTerm();
            }
        }
        return temp;
    }

    public long parseTerm() throws IOException, IllegalCharacterException, IllegalLexemeException {
        long temp = parseFlat();
        while (cur.type == LexemeType.MULT || cur.type == LexemeType.DIV) {
            LexemeType type = cur.type;
            cur = lexer.nextLexeme();
            if (type == LexemeType.MULT) {
                temp *= parseFlat();
            } else {
                temp /= parseFlat();
            }
        }
        return temp;
    }

    public long parseFlat() throws IllegalLexemeException, IOException, IllegalCharacterException {
        long temp = parsePower();
        if(cur.type == LexemeType.POW){
            cur = lexer.nextLexeme();
            temp = (long) Math.pow(temp, parseFlat());
        }
        return temp;
    }

    public long parsePower() throws IllegalLexemeException, IOException, IllegalCharacterException {
        if(cur.type == LexemeType.MINUS){
            cur = lexer.nextLexeme();
            return -parseAtom();
        } else {
            return parseAtom();
        }
    }

    public long parseAtom() throws IllegalLexemeException, IOException, IllegalCharacterException {
        long temp;
        switch (cur.type) {
            case NUMBER:
                temp = Long.parseLong(cur.value);
                cur = lexer.nextLexeme();
                break;
            default:
                throw new IllegalLexemeException("Unexpected", cur);
        }
        return temp;
    }
}
