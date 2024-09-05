# Flexible Calculator

## Overview

This project implements a flexible, extensible calculator following key object-oriented design principles. The calculator supports basic operations such as addition, subtraction, multiplication, and division, and is designed to allow new operations to be added easily without modifying the core logic of the `Calculator` class. The design also adheres to the **Open-Closed Principle**, ensuring that future operations can be integrated without changes to existing code.

## Features

- **Basic Operations**: Supports addition, subtraction, multiplication, and division.
- **Extensibility**: New operations can be added by implementing a common interface without changing the calculator's core functionality.
- **Chaining Operations**: The calculator supports chaining multiple operations sequentially.
- **Inversion of Control (IoC) Compatibility**: The project is structured in a way that allows external management of dependencies, supporting dependency injection frameworks.
- **Error Handling**: Gracefully handles unsupported operations and errors such as division by zero.
- **Unit Testing**: Includes comprehensive unit tests covering both normal and edge cases.

## Design

1. **Operation Enum**: An `Operation` enum is used to represent basic mathematical operations.
2. **Calculator Class**: The `Calculator` class is responsible for performing calculations. It accepts operations as input and performs them on two numbers.
3. **ICalculatorOperation Interface**: Each operation (e.g., addition, subtraction) implements the `ICalculatorOperation` interface, which allows easy extension without modifying the calculator's core.
4. **Chaining Operations**: The `Calculator` class supports chaining operations through methods that allow users to perform multiple operations sequentially.

## How to Add New Operations

To add a new operation:

1. Create a new class implementing the `ICalculatorOperation` interface.
2. Define the operation logic in the `perform` method.
3. Update the `operations.properties` file to include the new operation, mapping its name to the class implementation.

The calculator will automatically recognize and apply the new operation without requiring changes to the `Calculator` class.

### Example: Adding a Power Operation

1. Create a new class `PowerOperation.java`:

   ```java
   public class PowerOperation implements ICalculatorOperation {
       @Override
       public Number perform(Number num1, Number num2) {
           return Math.pow(num1.doubleValue(), num2.doubleValue());
       }
   }
   ```

2. Update `operations.properties` with:

   ```
   POWER=com.calculator.operations.PowerOperation
   ```

## Project Structure

```
/Calculator
│─── pom.xml                       # Project's Maven configuration
│─── README.md                     # Project's README file
│─── src
│    └─── main
│         └─── java
│              └─── com
│                   └─── calculator
│                        ├─── Calculator.java          # Core calculator logic
│                        ├─── CalculatorCLI.java       # Command-line interface for the calculator
│                        └─── operations
│                             ├─── AddOperation.java   # Addition operation
│                             ├─── SubtractOperation.java # Subtraction operation
│                             └─── MultiplyOperation.java # Multiplication operation
│                             └─── DivideOperation.java  # Division operation
│                   └─── common
│                        ├─── ICalculatorOperation.java  # Interface for operations
│                        └─── Operation.java            # Enum for supported operations
│    └─── resources
│         └─── operations.properties    # Properties file for managing operations
└─── test
     └─── java
          └─── com
               └─── calculator
                    └─── CalculatorTest.java     # Unit tests
```

## Requirements

- **Java 11+**
- **Maven**: Ensure Maven is installed to build and run the project.

## Setup and Usage

1. **Clone the repository**:

   ```
   git clone <repository-url>
   ```

2. **Build the project using Maven**:

   ```
   mvn clean install
   ```

3. **Run the calculator via the CLI**:

   ```
   java -jar target/calculator.jar
   ```

## Unit Tests

To run the unit tests:

```
mvn test
```

The test cases are located in the `src/test/java/com/calculator/CalculatorTest.java` file. They cover basic operations, chaining operations, and error handling cases like division by zero.

## Assumptions

- The calculator only supports binary operations (i.e., operations between two numbers).
- For simplicity, numbers are handled using Java's `Number` type, allowing support for both integers and floating-point numbers.
- Unsupported operations throw a meaningful exception to alert users of invalid actions.

## Extensibility and IoC

The project is designed with Inversion of Control (IoC) principles in mind, making it easy to integrate with dependency injection frameworks such as Spring or Guice.

## Error Handling

The calculator handles the following error scenarios:

- Division by zero: Throws an `ArithmeticException`.
- Unsupported operations: Returns an error message or throws an exception when attempting to use an unregistered operation.