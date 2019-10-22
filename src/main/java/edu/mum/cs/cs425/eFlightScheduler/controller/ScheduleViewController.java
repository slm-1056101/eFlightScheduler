package edu.mum.cs.cs425.eFlightScheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.cs.cs425.eFlightScheduler.models.Schedule;
import edu.mum.cs.cs425.eFlightScheduler.service.IScheduleService;

/**
 * Schedule controller
 */
@Controller
@RequestMapping(value = "/eflight/schedule")
public class ScheduleViewController {

    @Autowired
    private IScheduleService service;

    @GetMapping("/")
    public ModelAndView showSechduled() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("sechduled", service.getAll());
        mv.setViewName("public/schedule/index");
        List<Schedule> test = service.getAll();
        System.out.println(" the list is not empty"  +  test.size()); 
        return mv;
    }

}
