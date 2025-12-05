package com.nailton.spring_render.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BemvindoController {

    @GetMapping("/ola")
    public String sayHello() {
        return "Olá, Spring Boot está rodando!";
    }

    
}
