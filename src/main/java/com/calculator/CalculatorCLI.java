package com.calculator;

import com.calculator.common.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class CalculatorCLI {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorCLI.class);

    public static void main(String[] args) {
        logger.info("Starting Calculator Application...");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the initial value:");
        double initialValue = scanner.nextDouble();
        logger.info("Initial value entered: {}", initialValue);

        Calculator calculator = new Calculator(initialValue);

        boolean continueCalculation = true;
        while (continueCalculation) {
            System.out.println("Choose an operation: ADD, SUBTRACT, MULTIPLY, DIVIDE");
            String operationInput = scanner.next().toUpperCase();
            logger.info("Operation chosen: {}", operationInput);

            try {
                Operation operation = Operation.valueOf(operationInput);

                System.out.println("Enter the next value:");
                double nextValue = scanner.nextDouble();
                logger.info("Next value entered: {}", nextValue);

                calculator.chain(operation, nextValue);
                logger.info("Performed operation {} with value {}. Current result: {}", operation, nextValue, calculator.getCurrentValue());
                System.out.println("Current result: " + calculator.getCurrentValue());
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid operation entered: {}", operationInput);
                System.out.println("Invalid operation. Please choose from ADD, SUBTRACT, MULTIPLY, or DIVIDE.");
            } catch (ArithmeticException e) {
                logger.error("Arithmetic error occurred: {}", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Do you want to perform another operation? (yes/no)");
            String continueInput = scanner.next().toLowerCase();
            logger.info("User chose to continue: {}", continueInput);

            if (!continueInput.equals("yes")) {
                continueCalculation = false;
                logger.info("User ended the calculation session.");
            }
        }

        System.out.println("Final result: " + calculator.getCurrentValue());
        logger.info("Final result: {}", calculator.getCurrentValue());

        scanner.close();
    }
}
