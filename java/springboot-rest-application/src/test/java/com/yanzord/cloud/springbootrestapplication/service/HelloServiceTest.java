package com.yanzord.cloud.springbootrestapplication.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloServiceTest {

    private HelloService helloService = new HelloService();

    @Test
    public void shouldGetHello() {
        String name = "Yan";

        String actual = "Hello Yan";
        String expected = helloService.getHello(name);

        assertEquals(expected, actual);
    }
}
