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
import edu.mum.cs.cs425.eFlightScheduler.models.Runway;
import edu.mum.cs.cs425.eFlightScheduler.service.impl.FlightService;
import edu.mum.cs.cs425.eFlightScheduler.service.impl.RunwayService;

@Controller
@RequestMapping(value = "/eflight/runways")
public class RunwayController {
	
	@Autowired
	private RunwayService runwayService;
	
	@Autowired
	private RunwayController(RunwayService runwayService) {
		this.runwayService = runwayService;
	}
	
	@GetMapping(value = {"/list"})
    public ModelAndView listRunways() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("runways", runwayService.getAllRunways());
        modelAndView.setViewName("public/runways/list");
        return modelAndView;
    }
	
	@GetMapping(value = {"/new"})
    public String displayNewRunwayForm(Model model) {
        model.addAttribute("runway", new Runway());
        return "public/runways/new";
    }
	
    @PostMapping(value = {"/new"})
    public String addNewRunway(@Valid @ModelAttribute("runway") Runway runway,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "publilc/runways/new";
        }
        runway = runwayService.addNewRunway(runway);
        return "redirect:/eflight/runways/list";
    }
}
