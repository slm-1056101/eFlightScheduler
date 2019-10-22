package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;
import edu.mum.cs.cs425.eFlightScheduler.models.Runway;
import edu.mum.cs.cs425.eFlightScheduler.models.Schedule;
import edu.mum.cs.cs425.eFlightScheduler.models.Status;
import edu.mum.cs.cs425.eFlightScheduler.repository.ScheduleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {

    private ScheduleRepository repository = Mockito.mock(ScheduleRepository.class);

    @InjectMocks
    private ScheduleService service;

    @Test
    public void testSchedulingIsSuccessful() {
        Flight flight = new Flight("Some Flight", "Description");
        List<Runway> runways = Arrays.asList(new Runway(), new Runway());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.plus(5, ChronoUnit.SECONDS);
        Schedule schedule1 = new Schedule(now);
        Schedule schedule2 = new Schedule(next);
        when(repository.findFirst3ByRunwayOrderByTimeDesc(any()))
                .thenReturn(Arrays.asList(schedule1, schedule2));

        Schedule schedule = service.schedule(flight, Status.landing, LocalDateTime.now(), runways);

        Assert.assertEquals(next, schedule.getTime());
    }
}