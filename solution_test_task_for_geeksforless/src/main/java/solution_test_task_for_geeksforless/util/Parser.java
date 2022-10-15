package solution_test_task_for_geeksforless.util;

import java.util.*;

public class Parser {
    public static void main(String[] args) {
        String expressionText = "122 - 34 - 3* (55 + 5.0 * (3 - 2)) * 2";
        String expressionText2 = "(3.0 +2.2)";
        List<Lexeme> lexemes = lexAnalyze(expressionText);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
        System.out.println(Calculation.expr(lexemeBuffer));
        System.out.println(isValidBrackets(expressionText));
    }

    public static List<Lexeme> lexAnalyze(String expText) {
        if (isValidBrackets(expText) && isValidMathematicalNotation(expText)) {
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
                            int countDot = 0;
                            StringBuilder sb = new StringBuilder();
                            do {
                                sb.append(c);
                                pos++;
                                if (pos >= expText.length()) {
                                    break;
                                }
                                if (c == '.') {
                                    countDot++;
                                }

                                if (countDot >= 2) {
                                    return Collections.emptyList();
                                }

                                c = expText.charAt(pos);
                            } while (c <= '9' && c >= '0' || c == '.');
                            lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                        } else {
                            if (c != ' ') {
                                return Collections.emptyList();
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

    public static boolean isValidBrackets(String expression) {
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

    private static boolean isValidMathematicalNotation(String expression) {
        Map<Character, String> notations = new HashMap<>();
        notations.put('*', "Multiplication");
        notations.put('/', "Division");
        notations.put('+', "Plus");
        notations.put('-', "Minus");

        char[] array = expression.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (notations.containsKey(array[i])) {
                char nextChar = array[i + 1];
                if (nextChar == '*' || nextChar == '/' || nextChar == '+') {
                    return false;
                }
            }
        }
        return true;
    }
}