package edu.mum.cs.cs425.eFlightScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EFlightSchedulerApplication implements CommandLineRunner {

    @Autowired
    Test test;

    public static void main(String[] args) {
        SpringApplication.run(EFlightSchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        test.doMagic();
	}
}
