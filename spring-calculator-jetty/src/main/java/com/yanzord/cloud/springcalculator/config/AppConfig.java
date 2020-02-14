package com.yanzord.cloud.springcalculator.config;


import com.yanzord.cloud.springcalculator.service.Calculator;
import com.yanzord.cloud.springcalculator.operation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.yanzord.cloud.springcalculator")
public class AppConfig {

    @Bean
    public Calculator calculator() {
        Map<String, Operation> operations = new HashMap<>();

        operations.put("sum", new Sum());
        operations.put("sub", new Sub());
        operations.put("mult", new Multiply());
        operations.put("div", new Division());
        operations.put("pow", new Pow());

        return new Calculator(operations);
    }
}
