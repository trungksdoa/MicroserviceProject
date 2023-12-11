package com.module.userservice.controller;

import com.module.userservice.model.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestCOntroller {
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @RequestMapping("/service-instances/{serviceId}")
//    public ResponseEntity<BaseResponse> serviceInstances(@PathVariable String serviceId) {
//
//
//        for (int i = 0; i < discoveryClient.getServices().size(); i++) {
//            System.out.println("Index: " + i + ", Value: " + discoveryClient.getServices().get(i));
//        }
//        UrlServices urlServices = new UrlServices(discoveryClient);
//        System.out.println(urlServices.getServicesUrl("course"));
//
//        RestCall call = new RestCall(urlServices.getServicesUrl("course"));
//        BaseResponse response = call.doCall(HttpMethods.GET, "/api/course", null);
////        call.get(urlServices.getCourseServiceUrl() + "/api/course");
//
//
//        return new ResponseEntity<>(response, org.springframework.http.HttpStatus.OK);
//    }

    @PostMapping("/hello")
    public ResponseEntity<BaseResponse> test(@RequestBody  String name) {
        return new ResponseEntity<>(BaseResponse.builder().message(name).build(), org.springframework.http.HttpStatus.OK);
    }
}
