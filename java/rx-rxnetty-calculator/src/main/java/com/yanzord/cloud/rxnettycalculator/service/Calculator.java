package com.yanzord.cloud.rxnettycalculator.service;

import com.google.inject.Inject;
import com.yanzord.cloud.rxnettycalculator.exception.InvalidNumberException;
import com.yanzord.cloud.rxnettycalculator.exception.InvalidOperationException;
import com.yanzord.cloud.rxnettycalculator.operation.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Calculator {

	private Map<String, Operation> operations;

	private List<String> logs = new ArrayList<>();

	@Inject
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
