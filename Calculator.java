import java.util.*;

class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first number:");
        float a = scanner.nextFloat();

        System.out.println("Enter the operator (+, -, *, /, %):");
        char operator = scanner.next().charAt(0);

        System.out.println("Enter the second number:");
        float b = scanner.nextFloat();

        double result;

        switch (operator) {
            case '+':
                result = a + b;
                System.out.println("Result: " + result);
                break;
            case '-':
                result = a - b;
                System.out.println("Result: " + result);
                break;
            case '*':
                result = a * b;
                System.out.println("Result: " + result);
                break;
            case '/':
                if (b != 0) {
                    result = a / b;
                    System.out.println("Result: " + result);
                } else {
                    System.out.println("Error: Division by zero.");
                }
                break;
            case '%':
                result = a % b;
                System.out.println("Result: " + result);
                break;
            default:
                System.out.println("Error: Invalid operator.");
        }
    }
}
