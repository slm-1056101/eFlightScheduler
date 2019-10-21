package edu.mum.cs.cs425.eFlightScheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Schedule controller
 */
@Controller
@RequestMapping(value = "/eflight/schedule")
public class ScheduleViewController {

    @GetMapping("/")
    public String home() {
        return "public/schedule/index";
    }

}
