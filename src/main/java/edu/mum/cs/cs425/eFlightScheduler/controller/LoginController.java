package edu.mum.cs.cs425.eFlightScheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/eflight/public/login", "/public/login"})
    public String login() {
        return "public/login";
    }
}