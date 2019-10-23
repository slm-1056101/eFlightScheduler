package edu.mum.cs.cs425.eFlightScheduler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;
import edu.mum.cs.cs425.eFlightScheduler.service.impl.FlightService;

@Controller
@RequestMapping(value = "/eflight/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(value = {"/list"})
    public ModelAndView listFlights() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("flights", flightService.getAllFlights());
        modelAndView.setViewName("secured/flights/list");
        return modelAndView;
    }

    @GetMapping(value = {"/new"})
    public String displayNewFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "secured/flights/new";
    }

    @PostMapping(value = {"/new"})
    public String addNewFlight(@Valid @ModelAttribute("flight") Flight flight,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/flights/new";
        }
        flight = flightService.addNewFlight(flight);
        return "redirect:/eflight/flights/list";
    }

}
