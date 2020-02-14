package com.yanzord.cloud.guicepetshop.domain.job;

import com.yanzord.cloud.guicepetshop.domain.Pet;

public interface PetstoreJob {
    String execute(Pet pet);

    Double getValue();
}
