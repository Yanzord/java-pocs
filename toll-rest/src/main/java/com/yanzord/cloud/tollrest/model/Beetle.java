package com.yanzord.cloud.tollrest.model;

public class Beetle implements Vehicle {
    private static final Double TOLL_VALUE = 2.11;

    @Override
    public Double getTollValue() {
        return TOLL_VALUE;
    }
}
