package com.calculator;

import com.calculator.common.Operation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator(0);
        Number result = calculator.calculate(Operation.ADD, 2, 3);
        assertEquals(5.0, result.doubleValue());
    }

    @Test
    public void testSubtraction() {
        Calculator calculator = new Calculator(0);
        Number result = calculator.calculate(Operation.SUBTRACT, 5, 3);
        assertEquals(2.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testMultiplication() {
        Calculator calculator = new Calculator(1);
        Number result = calculator.calculate(Operation.MULTIPLY, 2, 3);
        assertEquals(6.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testDivision() {
        Calculator calculator = new Calculator(10);
        Number result = calculator.calculate(Operation.DIVIDE, 10, 2);
        assertEquals(5.0, result.doubleValue(), 0.0001);
    }

    @Test
    public void testDivisionByZero() {
        Calculator calculator = new Calculator(0);
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(Operation.DIVIDE, 10, 0);
        });
        assertEquals("Cannot divide by zero.", exception.getMessage());
    }

    @Test
    public void testChainingOperations() {
        Calculator calculator = new Calculator(5);
        Number result = calculator
                .chain(Operation.ADD, 3)
                .chain(Operation.MULTIPLY, 2)
                .getCurrentValue();
        assertEquals(16.0, result.doubleValue());
    }

    @Test
    public void testUnsupportedOperation() {
        Calculator calculator = new Calculator(0);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            calculator.calculate(null, 10, 3);
        });
        assertEquals("Operation not supported.", exception.getMessage());
    }

}
