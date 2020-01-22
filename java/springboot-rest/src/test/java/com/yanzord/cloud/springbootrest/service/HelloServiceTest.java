package com.yanzord.cloud.springbootrest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes={HelloService.class})
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void shouldGetHello() {
        String name = "Yan";

        String actual = "Hello Yan";
        String expected = helloService.getHello(name);

        assertEquals(expected, actual);
    }
}
