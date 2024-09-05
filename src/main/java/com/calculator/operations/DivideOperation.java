package com.calculator.operations;

import com.calculator.common.ICalculatorOperation;

public class DivideOperation implements ICalculatorOperation {
    @Override
    public Number apply(Number num1, Number num2) {
        if (num2.doubleValue() == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return num1.doubleValue() / num2.doubleValue();
    }
}