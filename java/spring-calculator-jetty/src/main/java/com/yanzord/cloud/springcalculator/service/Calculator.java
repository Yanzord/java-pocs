package com.yanzord.cloud.springcalculator.service;

import com.yanzord.cloud.springcalculator.exception.InvalidNumberException;
import com.yanzord.cloud.springcalculator.exception.InvalidOperationException;
import com.yanzord.cloud.springcalculator.operation.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Calculator {

    private Map<String, Operation> operations;

    private List<String> logs = new ArrayList<>();

    public Calculator(Map<String, Operation> operations) {
        this.operations = operations;
    }

    public List<String> getLogs() {
        return logs;
    }

    public Double doOperation(Double firstValue, String operationRequested, Double secondValue) throws InvalidNumberException, InvalidOperationException {
        if (firstValue == null || secondValue == null)
            throw new InvalidNumberException("One of the values inserted is null!");

        Optional<Operation> operation = Optional.ofNullable(operations.get(operationRequested));

        if (!operation.isPresent()) {
            throw new InvalidOperationException("Operation inserted doesn't exist.");
        }

        Double result;

        try {
            result = operation.get().execute(firstValue, secondValue);
        } catch (InvalidNumberException e) {
            logs.add(e.getMessage());
            throw e;
        }

        logs.add("Operation: " + operationRequested + ", first value: " + firstValue + ", second value: " + secondValue + ", result: " + result);

        return result;
    }
}
