package com.yanzord.cloud.guicepetshop.domain.job;

import com.yanzord.cloud.guicepetshop.domain.Pet;

public class LongHaircut implements PetstoreJob {
    private final Double VALUE = 35.0;

    @Override
    public String execute(Pet pet) {
        return "Cutting long haircut: " + pet.getName();
    }

    @Override
    public Double getValue() {
        return VALUE;
    }
}
