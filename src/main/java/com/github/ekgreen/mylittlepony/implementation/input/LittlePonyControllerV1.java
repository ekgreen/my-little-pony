package com.github.ekgreen.mylittlepony.implementation.input;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/api")
@RestController
public class LittlePonyControllerV1 {


    @GetMapping("/hello")
    public String helloPony() {
        return "Hi, my cloud friend!";
    }
}
