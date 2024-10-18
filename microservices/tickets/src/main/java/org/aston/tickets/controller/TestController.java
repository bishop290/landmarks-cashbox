package org.aston.tickets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test3")
public class TestController {

    @GetMapping
    public String test() {
        return "Hello my friend! Its me, Tickets";
    }
}
