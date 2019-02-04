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

    @Test
    public void parseTerm() throws IOException, IllegalCharacterException, IllegalLexemeException {
        assert newParser("5 * 8 / 4").parseTerm() == 10;
        assert newParser("6 / 3 * 5").parseTerm() == 10;
        try {
            newParser("2 * 5 /").parseTerm();
            assert false;
        } catch (IllegalLexemeException e){
            assert true;
        }
    }

    @Test
    public void parseFlat() throws IOException, IllegalCharacterException, IllegalLexemeException {
        assert newParser("2 ^ 3 ^ 2").parseFlat() == 512;
    }

    @Test
    public void parsePower() throws IOException, IllegalCharacterException, IllegalLexemeException {
        assert newParser("-67").parsePower() == -67;
        assert newParser("983").parsePower() == 983;
    }

    @Test
    public void parseAtom() throws IOException, IllegalCharacterException, IllegalLexemeException {
        assert newParser("78").parseAtom() == 78;
        assert newParser("(84)").parseAtom() == 84;
        assert newParser("(2+3)").parseAtom() == 5;
        try {
            newParser("(4+8").parseAtom();
            assert false;
        } catch (IllegalLexemeException e){
            assert true;
        }
    }
}