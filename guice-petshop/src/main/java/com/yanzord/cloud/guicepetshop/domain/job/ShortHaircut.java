package com.yanzord.cloud.guicepetshop.domain.job;

import com.yanzord.cloud.guicepetshop.domain.Pet;

public class ShortHaircut implements PetstoreJob {
    private final Double VALUE = 30.0;

    @Override
    public String execute(Pet pet) {
        return "Cutting short haircut: " + pet.getName();
    }

    @Override
    public Double getValue() {
        return VALUE;
    }
}
