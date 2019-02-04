package ru.nsu.fit.cherniak.expr;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {

    Parser newParser(String f) throws IOException, IllegalCharacterException {
        return new Parser(
                new Lexer(
                        new StringReader(f)
                )
        );
    }

    @Test
    public void parseExpr() throws IOException, IllegalCharacterException, IllegalLexemeException {
        assert newParser("12 + 34 - 12").parseExpr() == 34;
        assert newParser("3 - 4 + 8").parseExpr() == 7;
        try {
            newParser("2 + 5 -").parseExpr();
            assert false;
        } catch (IllegalLexemeException e){
            assert true;
        }
    }
}