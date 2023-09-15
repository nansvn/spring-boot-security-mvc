package com.springproject.securitymvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    // add a request mapping for /leaders
    @GetMapping("/managers")
    public String showLeaders() {
        return "manager";
    }

    // add request mapping for /systems
    @GetMapping("/admins")
    public String showSystems() {
        return "admin";
    }

}









