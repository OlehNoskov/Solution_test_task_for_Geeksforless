package solution_test_task_for_geeksforless.util;

import java.util.*;

public class Parser {
    public static void main(String[] args) {
        String expressionText = "122 - 34 - 3* (55 + 5* (3 - 2)) * 2";
        String expressionText2 = "(3.0 +2.2)";
        List<Lexeme> lexemes = lexAnalyze(expressionText);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
        System.out.println(expr(lexemeBuffer));
        System.out.println(isValidBrackets(expressionText));
    }

    public static List<Lexeme> lexAnalyze(String expText) {
        if (isValidBrackets(expText)) {
            ArrayList<Lexeme> lexemes = new ArrayList<>();
            int pos = 0;
            while (pos < expText.length()) {
                char c = expText.charAt(pos);
                switch (c) {
                    case '(':
                        lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                        pos++;
                        continue;
                    case ')':
                        lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                        pos++;
                        continue;
                    case '+':
                        lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                        pos++;
                        continue;
                    case '-':
                        lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                        pos++;
                        continue;
                    case '*':
                        lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                        pos++;
                        continue;
                    case '/':
                        lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                        pos++;
                        continue;
                    default:
                        if (c <= '9' && c >= '0') {
                            StringBuilder sb = new StringBuilder();
                            do {
                                sb.append(c);
                                pos++;
                                if (pos >= expText.length()) {
                                    break;
                                }
                                c = expText.charAt(pos);
                            } while (c <= '9' && c >= '0' || c == '.');
                            lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                        } else {
                            if (c != ' ') {
                                throw new RuntimeException("Unexpected character: " + c);
                            }
                            pos++;
                        }
                }
            }
            lexemes.add(new Lexeme(LexemeType.EOF, ""));
            return lexemes;
        } else {
            return Collections.emptyList();
        }
    }

    public static double expr(LexemeBuffer lexemes) {
        if (!lexemes.getLexemes().isEmpty()) {
            Lexeme lexeme = lexemes.next();
            if (lexeme.type == LexemeType.EOF) {
                return 0;
            } else {
                lexemes.back();
                return plusminus(lexemes);
            }
        } else {
            return 0;
        }
    }

    public static double plusminus(LexemeBuffer lexemes) {
        double value = multdiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS:
                    value += multdiv(lexemes);
                    break;
                case OP_MINUS:
                    value -= multdiv(lexemes);
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

    public static double multdiv(LexemeBuffer lexemes) {
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
            case LEFT_BRACKET:
                double value = plusminus(lexemes);
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

    private static boolean isValidBrackets(String expression) {
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(')', '(');

        Deque<Character> stack = new LinkedList<>();
        for (char c : expression.toCharArray()) {
            if (brackets.containsValue(c)) {
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                    return false;
                }
            }
        }
        // in the end stack must be is empty
        return stack.isEmpty();
    }
}