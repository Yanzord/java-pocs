package com.yanzord.cloud.springbootrestapplication.controller;

import com.yanzord.cloud.springbootrestapplication.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping
    @ResponseBody
    private String getHelloDefault() {
        return "Hello anon";
    }

    @RequestMapping("/{name}")
    @ResponseBody
    private String getHello(@PathVariable(value = "name") String name) {
        return helloService.getHello(name);
    }
}
