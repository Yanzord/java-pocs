package com.yanzord.cloud.springcalculator.operation;

import com.yanzord.cloud.springcalculator.exception.InvalidNumberException;

public interface Operation {
    Double execute(Double firstValue, Double secondValue) throws InvalidNumberException;
}
