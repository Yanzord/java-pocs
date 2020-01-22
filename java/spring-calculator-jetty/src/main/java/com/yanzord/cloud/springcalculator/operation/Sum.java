package com.yanzord.cloud.springcalculator.operation;

public class Sum implements Operation {
    @Override
    public Double execute(Double firstValue, Double secondValue) {
        return firstValue + secondValue;
    }
}
