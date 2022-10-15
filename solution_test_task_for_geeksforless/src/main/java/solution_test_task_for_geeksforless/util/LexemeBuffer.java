package solution_test_task_for_geeksforless.util;

import java.util.List;

public class LexemeBuffer {
    private int position;

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

    public int getPosition() {
        return position;
    }

    public List<Lexeme> getLexemes() {
        return lexemes;
    }
}