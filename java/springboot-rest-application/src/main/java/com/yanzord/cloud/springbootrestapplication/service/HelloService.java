package com.yanzord.cloud.springbootrestapplication.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getHello(String name) {
        return "Hello " + name;
    }
}
