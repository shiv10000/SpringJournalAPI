package com.example.Journal.app.controller;


import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/health-check")
    public String HealthCheck(){
        return "Okiee";
    }

}
