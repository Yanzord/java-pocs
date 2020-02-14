package com.yanzord.cloud.tollrest.model;

public class Bus implements Vehicle {
    private static final Double TOLL_VALUE = 1.59;

    @Override
    public Double getTollValue() {
        return TOLL_VALUE;
    }
}
