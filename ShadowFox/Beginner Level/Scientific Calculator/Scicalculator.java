import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Scicalculator { // Changed class name here
    private static double memory = 0;
    private static List<String> history = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalc = true;

        while (continueCalc) {
            System.out.println("Welcome to Enhanced Calculator - Scicalculator"); // Optional: update display name
            System.out.println("Choose an operation:");
            System.out.println("1: Add\n2: Subtract\n3: Multiply\n4: Divide\n5: Square Root\n6: Exponentiation");
            System.out.println("7: Trigonometric Functions (Sin, Cos, Tan)\n8: Logarithmic Functions\n9: Factorial");
            System.out.println("10: Memory Store (M+)\n11: Memory Recall (MR)\n12: Memory Clear (MC)");
            System.out.println("13: Temperature Conversion\n14: Currency Conversion\n15: View History\n16: Save Results to File\n17: Exit");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1: // Add
                        System.out.println("Enter two numbers:");
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        double sum = add(a, b);
                        System.out.println("Result: " + df.format(sum));
                        history.add("Add: " + a + " + " + b + " = " + sum);
                        break;
                    case 2: // Subtract
                        System.out.println("Enter two numbers:");
                        a = scanner.nextDouble();
                        b = scanner.nextDouble();
                        double difference = subtract(a, b);
                        System.out.println("Result: " + df.format(difference));
                        history.add("Subtract: " + a + " - " + b + " = " + difference);
                        break;
                    case 3: // Multiply
                        System.out.println("Enter two numbers:");
                        a = scanner.nextDouble();
                        b = scanner.nextDouble();
                        double product = multiply(a, b);
                        System.out.println("Result: " + df.format(product));
                        history.add("Multiply: " + a + " * " + b + " = " + product);
                        break;
                    case 4: // Divide
                        System.out.println("Enter two numbers:");
                        a = scanner.nextDouble();
                        b = scanner.nextDouble();
                        if (b == 0) {
                            System.out.println("Error: Cannot divide by zero.");
                        } else {
                            double quotient = divide(a, b);
                            System.out.println("Result: " + df.format(quotient));
                            history.add("Divide: " + a + " / " + b + " = " + quotient);
                        }
                        break;
                    case 5: // Square Root
                        System.out.println("Enter a number:");
                        a = scanner.nextDouble();
                        if (a < 0) {
                            System.out.println("Error: Cannot calculate square root of a negative number.");
                        } else {
                            double sqrt = Math.sqrt(a);
                            System.out.println("Result: " + df.format(sqrt));
                            history.add("Square Root: sqrt(" + a + ") = " + sqrt);
                        }
                        break;
                    case 6: // Exponentiation
                        System.out.println("Enter the base and exponent:");
                        a = scanner.nextDouble();
                        b = scanner.nextDouble();
                        double exp = Math.pow(a, b);
                        System.out.println("Result: " + df.format(exp));
                        history.add("Exponentiation: " + a + "^" + b + " = " + exp);
                        break;
                    case 7: // Trigonometric Functions
                        System.out.println("Enter an angle in degrees:");
                        a = Math.toRadians(scanner.nextDouble());
                        System.out.println("sin: " + Math.sin(a));
                        System.out.println("cos: " + Math.cos(a));
                        System.out.println("tan: " + Math.tan(a));
                        history.add("Trigonometric Functions: sin(" + a + "), cos(" + a + "), tan(" + a + ")");
                        break;
                    case 8: // Logarithmic Functions
                        System.out.println("Enter a number:");
                        a = scanner.nextDouble();
                        if (a <= 0) {
                            System.out.println("Error: Logarithms require positive numbers.");
                        } else {
                            System.out.println("log10: " + Math.log10(a));
                            System.out.println("ln: " + Math.log(a));
                            history.add("Logarithmic Functions: log10(" + a + "), ln(" + a + ")");
                        }
                        break;
                    case 9: // Factorial
                        System.out.println("Enter a number:");
                        int num = scanner.nextInt();
                        if (num < 0) {
                            System.out.println("Error: Factorial is not defined for negative numbers.");
                        } else {
                            System.out.println("Factorial: " + factorial(num));
                            history.add("Factorial: " + num + "! = " + factorial(num));
                        }
                        break;
                    case 10: // Memory Store
                        System.out.println("Enter a number to store in memory:");
                        memory = scanner.nextDouble();
                        System.out.println("Stored in memory: " + memory);
                        break;
                    case 11: // Memory Recall
                        System.out.println("Memory Recall: " + memory);
                        break;
                    case 12: // Memory Clear
                        memory = 0;
                        System.out.println("Memory Cleared");
                        break;
                    case 13: // Temperature Conversion
                        System.out.println("Enter temperature in Celsius:");
                        a = scanner.nextDouble();
                        double fahrenheit = (a * 9/5) + 32;
                        System.out.println("Temperature in Fahrenheit: " + df.format(fahrenheit));
                        history.add("Temperature Conversion: " + a + "C = " + fahrenheit + "F");
                        break;
                    case 14: // Currency Conversion
                        System.out.println("Enter amount in USD:");
                        a = scanner.nextDouble();
                        double eur = a * 0.85; // Static conversion rate
                        System.out.println("Amount in EUR: " + df.format(eur));
                        history.add("Currency Conversion: " + a + " USD = " + eur + " EUR");
                        break;
                    case 15: // View History
                        System.out.println("Calculation History:");
                        for (String record : history) {
                            System.out.println(record);
                        }
                        break;
                    case 16: // Save Results to File
                        saveResultsToFile();
                        break;
                    case 17: // Exit
                        continueCalc = false;
                        System.out.println("Exiting the calculator. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Basic Operations
    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static double divide(double a, double b) { return a / b; }

    // Factorial Function
    public static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    // Save Results to File
    public static void saveResultsToFile() {
        try {
            FileWriter writer = new FileWriter("calculator_history.txt");
            for (String record : history) {
                writer.write(record + "\n");
            }
            writer.close();
            System.out.println("History saved to calculator_history.txt");
        } catch (IOException e) {
            System.out.println("Error: Could not save file.");
        }
    }
}
