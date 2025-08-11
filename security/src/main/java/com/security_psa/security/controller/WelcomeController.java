package com.security_psa.security.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class WelcomeController {
    
    @GetMapping("/welcome")
    public String sayHello() {
        return "Hello, Welcome to the Security Application!";
    }
    @GetMapping("/goodbye")
    public String sayGoodbye() {
        return "Goodbye, see you next time!";
    }  

    
}
