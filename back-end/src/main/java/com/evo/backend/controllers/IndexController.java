package com.evo.backend.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String index(){
        return "index14444";
    }

}
