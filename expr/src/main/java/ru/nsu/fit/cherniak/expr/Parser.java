package ru.nsu.fit.cherniak.expr;

public class Parser {
    private Lexer lexer;
    private Lexeme cur;

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }
}
