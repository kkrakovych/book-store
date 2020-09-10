package com.kosto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public String ping() {
        return "Up and Running";
    }

}
