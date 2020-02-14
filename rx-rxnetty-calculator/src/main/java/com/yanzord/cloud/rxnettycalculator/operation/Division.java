package com.yanzord.cloud.rxnettycalculator.operation;

import com.yanzord.cloud.rxnettycalculator.exception.InvalidNumberException;

public class Division implements Operation {
	@Override
	public Double execute(Double firstValue, Double secondValue) throws InvalidNumberException {
		if (secondValue == 0) {
			throw new InvalidNumberException("Divided by zero: " + firstValue + " / " + secondValue);
		}

		return firstValue / secondValue;
	}
}
