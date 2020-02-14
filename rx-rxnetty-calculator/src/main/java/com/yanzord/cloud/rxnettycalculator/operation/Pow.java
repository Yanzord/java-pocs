package com.yanzord.cloud.rxnettycalculator.operation;

public class Pow implements Operation {
	@Override
	public Double execute(Double firstValue, Double secondValue) {
		return Math.pow(firstValue, secondValue);
	}
}
