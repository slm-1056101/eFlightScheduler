package edu.mum.cs.cs425.eFlightScheduler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EFlightSchedulerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EFlightSchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Running EFlight Scheduling System");
    }
}
