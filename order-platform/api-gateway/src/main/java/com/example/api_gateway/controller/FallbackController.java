package com.example.api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/fallback/product")
    public ResponseEntity<String> productFallBack(){
       return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("product service is down");
    }

    @RequestMapping("/fallback/order")
    public ResponseEntity<String> orderFallback(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("order service is down");
    }
}
