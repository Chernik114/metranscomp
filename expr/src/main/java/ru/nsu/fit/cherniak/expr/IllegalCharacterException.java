package ru.nsu.fit.cherniak.expr;

public class IllegalCharacterException extends Exception {
    public IllegalCharacterException(String s, char ch){
        super(String.format("%s '%c'", s, ch));
    }

    public IllegalCharacterException(String s, int ch){
        this(s, (char)ch);
    }
}
