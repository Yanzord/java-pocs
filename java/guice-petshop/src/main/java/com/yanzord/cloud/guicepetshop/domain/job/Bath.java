package com.yanzord.cloud.guicepetshop.domain.job;

import com.yanzord.cloud.guicepetshop.domain.Pet;

public class Bath implements PetstoreJob {
    private final Double VALUE = 20.0;

    @Override
    public String execute(Pet pet) {
        return "Giving bath: " + pet.getName();
    }

    @Override
    public Double getValue() {
        return VALUE;
    }
}
