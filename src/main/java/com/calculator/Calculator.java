package com.calculator;

import com.calculator.common.ICalculatorOperation;
import com.calculator.common.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Calculator {

    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);
    private final Map<String, ICalculatorOperation> operations = new HashMap<>();
    private Number currentValue;

    public Calculator(Number initialValue) {
        this.currentValue = initialValue;
        loadOperations();
    }

    private void loadOperations() {
        logger.info("Loading operations from properties file...");
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("operations.properties")) {
            if (input == null) {
                logger.error("Failed to load operations.properties file.");
                return;
            }
            props.load(input);

            for (String opName : props.stringPropertyNames()) {
                String className = props.getProperty(opName);
                try {
                    Class<?> clazz = Class.forName(className);
                    ICalculatorOperation operation = (ICalculatorOperation) clazz.getDeclaredConstructor().newInstance();
                    operations.put(opName, operation);
                    logger.debug("Successfully loaded operation: {}", opName);
                } catch (Exception e) {
                    logger.error("Failed to load operation: {}", opName, e);
                }
            }
        } catch (IOException ex) {
            logger.error("Error reading the operations.properties file", ex);
        }
    }

    public Number calculate(Operation op, Number num1, Number num2) {
        logger.debug("Performing calculation: {} {} {}", num1, op, num2);
        ICalculatorOperation operation;
        try {
            operation = operations.get(op.name());
        } catch (NullPointerException e) {
            logger.warn("Operation not supported: {}", op);
            throw new UnsupportedOperationException("Operation not supported.");
        }
        return operation.apply(num1, num2);
    }

    public Calculator chain(Operation op, Number value) {
        logger.debug("Chaining operation: {} with value {}", op, value);
        this.currentValue = calculate(op, this.currentValue, value);
        return this;
    }

    public Number getCurrentValue() {
        logger.info("Returning current value: {}", this.currentValue);
        return this.currentValue;
    }
}
