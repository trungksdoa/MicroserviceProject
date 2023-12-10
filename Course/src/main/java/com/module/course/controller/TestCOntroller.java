package com.module.course.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestCOntroller {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances")
    public List<ServiceInstance> serviceInstances() {


        for (int i = 0; i < discoveryClient.getServices().size(); i++) {
            System.out.println("Index: " + i + ", Value: " + discoveryClient.getServices().get(i));
        }
        return this.discoveryClient.getInstances("userService");
    }
}
