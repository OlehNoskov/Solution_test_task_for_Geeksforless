package solution_test_task_for_geeksforless.util;

import lombok.Getter;

import java.util.List;

public class LexemeBuffer {
    @Getter
    private int position;

    @Getter
    public List<Lexeme> lexemes;

    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Lexeme next() {
        return lexemes.get(position++);
    }

    public void back() {
        position--;
    }
}