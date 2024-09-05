package com.calculator.operations;

import com.calculator.common.ICalculatorOperation;

public class MultiplyOperation implements ICalculatorOperation {
    @Override
    public Number apply(Number num1, Number num2) {
        return num1.doubleValue() * num2.doubleValue();
    }
}