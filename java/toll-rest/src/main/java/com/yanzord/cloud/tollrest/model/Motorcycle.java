package com.yanzord.cloud.tollrest.model;

public class Motorcycle implements Vehicle {
    private static final Double TOLL_VALUE = 1d;

    @Override
    public Double getTollValue() {
        return TOLL_VALUE;
    }
}
