package com.yanzord.cloud.tollrest.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.yanzord.cloud.tollrest.controller.TollServiceController;
import com.yanzord.cloud.tollrest.model.*;
import com.yanzord.cloud.tollrest.service.TollService;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer() {
        final JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(tollRestService(),JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.asList(tollRestService()));
        factory.setProviders(Arrays.asList(jsonProvider()));
        return factory.create();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    @Bean
    public TollServiceController tollRestService() {
        return new TollServiceController(tollService());
    }

    @Bean
    public TollService tollService() {
        Map<String, Vehicle> vehicles = new HashMap<>();

        vehicles.put("bus", new Bus());
        vehicles.put("motorcycle", new Motorcycle());
        vehicles.put("bike", new Bike());
        vehicles.put("truck", new Truck());
        vehicles.put("beetle", new Beetle());

        return new TollService(vehicles);
    }
}
