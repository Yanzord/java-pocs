package com.yanzord.cloud.tollrest.model;

public class Truck implements Vehicle {
    private static final Double TOLL_VALUE = 3.95;

    @Override
    public Double getTollValue() {
        return TOLL_VALUE;
    }
}
