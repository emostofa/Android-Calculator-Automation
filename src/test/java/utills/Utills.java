package utills;

import java.util.Stack;

public class Utills {
    public String calculateSeries(String expression) {
        // Remove any spaces from the input expression
        expression = expression.replaceAll(" ", "");

        // Create two stacks, one for numbers and one for operators
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        // Define precedence of operators
        // Higher value means higher precedence
        int precedence[] = {0, 1, 1, 2, 2}; // + and - have lower precedence than * and /, and 0 for others

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            // If the current character is a digit, extract the whole number
            if (Character.isDigit(currentChar)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--; // Move back one step to account for the extra increment in the loop

                // Push the number onto the numbers stack
                numbers.push(num);
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                // Pop and calculate while the current operator has lower or equal precedence to the top operator
                while (!operators.isEmpty() && precedence[getOperatorPrecedence(currentChar)] <= precedence[getOperatorPrecedence(operators.peek())]) {
                    int num2 = numbers.pop();
                    int num1 = numbers.pop();
                    char op = operators.pop();
                    int result = performOperation(num1, num2, op);
                    numbers.push(result);
                }

                // Push the current operator onto the operators stack
                operators.push(currentChar);
            }
        }

        // Perform any remaining calculations in the stacks
        while (!operators.isEmpty()) {
            int num2 = numbers.pop();
            int num1 = numbers.pop();
            char op = operators.pop();
            int result = performOperation(num1, num2, op);
            numbers.push(result);
        }

        // The final result is on top of the numbers stack
        return String.valueOf(numbers.pop());
    }

    private static int getOperatorPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            default:
                return 2; // For other characters (not operators)
        }
    }

    private static int performOperation(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }


}
