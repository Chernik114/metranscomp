package ru.nsu.fit.cherniak.expr;

import java.util.Objects;

public class Lexeme {
    public LexemeType type;
    public String value;

    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lexeme)) return false;
        Lexeme lexeme = (Lexeme) o;
        return type == lexeme.type &&
                Objects.equals(value, lexeme.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, value);
    }
}
