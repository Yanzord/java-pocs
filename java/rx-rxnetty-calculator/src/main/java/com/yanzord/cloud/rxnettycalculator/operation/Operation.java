package com.yanzord.cloud.rxnettycalculator.operation;

import com.yanzord.cloud.rxnettycalculator.exception.InvalidNumberException;

public interface Operation {
	Double execute(Double firstValue, Double secondValue) throws InvalidNumberException;
}
