package com.yanzord.cloud.guicepetshop.domain;

import java.util.UUID;

public class Pet {
    private String id;
    private String name;
    private String race;
    private Integer age;

    public Pet(String name, String race, Integer age) {
        this.name = name;
        this.race = race;
        this.age = age;
        this.id = UUID.randomUUID().toString().replace("-", "");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Race: " + race +
                " | Age: " + age;
    }
}
