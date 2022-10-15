package solution_test_task_for_geeksforless.util;

public class Calculation {

    public static double expr(LexemeBuffer lexemes) {
        if (!lexemes.getLexemes().isEmpty()) {
            Lexeme lexeme = lexemes.next();
            if (lexeme.type == LexemeType.EOF) {
                return 0;
            } else {
                lexemes.back();
                return plusMinus(lexemes);
            }
        } else {
            return 0;
        }
    }

    public static double plusMinus(LexemeBuffer lexemes) {
        double value = multiplicationDivision(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS:
                    value += multiplicationDivision(lexemes);
                    break;
                case OP_MINUS:
                    value -= multiplicationDivision(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPosition());
            }
        }
    }

    public static double multiplicationDivision(LexemeBuffer lexemes) {
        double value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case OP_PLUS:
                case OP_MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPosition());
            }
        }
    }

    public static double factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NUMBER:
                return Double.parseDouble(lexeme.value);
            case OP_MINUS:
                double value = factor(lexemes);
                return -value;
            case LEFT_BRACKET:
                value = plusMinus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPosition());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPosition());
        }
    }
}