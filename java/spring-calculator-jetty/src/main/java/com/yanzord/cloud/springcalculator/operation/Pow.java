package com.yanzord.cloud.springcalculator.operation;

public class Pow implements Operation {
    @Override
    public Double execute(Double firstValue, Double secondValue) {
        return Math.pow(firstValue, secondValue);
    }
}
