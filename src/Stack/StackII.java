package Stack;
import java.util.Stack;
public class StackII {
    public static void main(String[] args) {

    }
}


class ArithmeticExpressionEvaluation {
    public static int evaluateExpression(String expression) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder operand = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    operand.append(expression.charAt(i));
                    i++;
                }
                i--;

                operandStack.push(Integer.parseInt(operand.toString()));
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (operatorStack.peek() != '(') {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operatorStack.isEmpty() && hasPrecedence(c, operatorStack.peek())) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            performOperation(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        } else if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static void performOperation(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        int operand2 = operandStack.pop();
        int operand1 = operandStack.pop();
        char operator = operatorStack.pop();

        int result;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }

        operandStack.push(result);
    }

}
