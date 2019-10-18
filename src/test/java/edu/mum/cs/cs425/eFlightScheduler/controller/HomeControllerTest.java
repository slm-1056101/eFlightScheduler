package edu.mum.cs.cs425.eFlightScheduler.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
    @Test
    public void testDisplayHomePage() throws Exception {
        mockMvc.perform(get("/eflight"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/index"))
                .andExpect(content().string(
                        containsString("<h3>Welcome to eFlight Scheduling System</h3>")));
    }

}
