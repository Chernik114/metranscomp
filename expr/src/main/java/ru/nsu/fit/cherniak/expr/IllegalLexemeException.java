package ru.nsu.fit.cherniak.expr;

public class IllegalLexemeException extends Exception {
    public IllegalLexemeException(String s, Lexeme l){
        super(String.format("%s '%s'", s, l.value));
    }
}
