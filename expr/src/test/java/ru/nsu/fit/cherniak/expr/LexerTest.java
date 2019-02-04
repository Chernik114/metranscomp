package ru.nsu.fit.cherniak.expr;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void nextLexeme() throws IOException, IllegalCharacterException {
        Lexer lexer = new Lexer(
                new StringReader("12+34-56")
        );
        assert lexer.nextLexeme().equals(new Lexeme(LexemeType.NUMBER, "12"));
        assert lexer.nextLexeme().equals(new Lexeme(LexemeType.PLUS, "+"));
        assert lexer.nextLexeme().equals(new Lexeme(LexemeType.NUMBER, "34"));
        assert lexer.nextLexeme().equals(new Lexeme(LexemeType.MINUS, "-"));
        assert lexer.nextLexeme().equals(new Lexeme(LexemeType.NUMBER, "56"));
    }

}