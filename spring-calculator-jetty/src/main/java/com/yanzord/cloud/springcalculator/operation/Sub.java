package com.yanzord.cloud.springcalculator.operation;

public class Sub implements Operation{
    @Override
    public Double execute(Double firstValue, Double secondValue) {
        return firstValue - secondValue;
    }
}
