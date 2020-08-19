package br.com.higorcoliveira.dockerk8s.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value( "${my.config}" )
    private String myConfig;

    @GetMapping("/")
    public String sayHi() {
        return "Hi from Dockerk8s and " + this.myConfig;
    }
}
