package com.yanzord.cloud.tollrest.model;

public class Bike implements Vehicle {
    private static final Double TOLL_VALUE = 0.49;

    @Override
    public Double getTollValue() {
        return TOLL_VALUE;
    }
}
