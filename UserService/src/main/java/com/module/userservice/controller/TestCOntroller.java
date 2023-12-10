package com.module.userservice.controller;

import com.module.userservice.enums.HttpMethods;
import com.module.userservice.model.BaseResponse;
import com.module.userservice.restTemplate.RestCall;
import com.module.userservice.restTemplate.UrlServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestCOntroller {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{serviceId}")
    public ResponseEntity<BaseResponse> serviceInstances(@PathVariable String serviceId) {


        for (int i = 0; i < discoveryClient.getServices().size(); i++) {
            System.out.println("Index: " + i + ", Value: " + discoveryClient.getServices().get(i));
        }
        UrlServices urlServices = new UrlServices(discoveryClient);
        System.out.println(urlServices.getServicesUrl("course"));

        RestCall call = new RestCall(urlServices.getServicesUrl("course"));
        BaseResponse response = call.doCall(HttpMethods.GET, "/api/course", null);
//        call.get(urlServices.getCourseServiceUrl() + "/api/course");


        return new ResponseEntity<>(response, org.springframework.http.HttpStatus.OK);
    }
}
