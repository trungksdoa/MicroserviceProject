package com.module.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.module.course.repositorys")

@EnableDiscoveryClient
public class CourseApplication {


    private static void ApplicationRun(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

}
