package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import edu.mum.cs.cs425.eFlightScheduler.models.Schedule;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchedulerTest {

    private Scheduler scheduler;

    @Before
    public void setup() {
        scheduler = new Scheduler(Duration.of(3, ChronoUnit.SECONDS));
    }

    @Test
    public void testScheduleIsReservable() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.plus(5, ChronoUnit.SECONDS);
        Schedule schedule1 = new Schedule(now);
        scheduler.add(schedule1);
        Schedule schedule2 = new Schedule(next);

        boolean reservable2 = scheduler.isReservable(schedule2);
        assertTrue(reservable2);

    }

    @Test
    public void testScheduleIsNotReservable() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.plus(2, ChronoUnit.SECONDS);

        Schedule schedule1 = new Schedule(now);
        Schedule schedule2 = new Schedule(next);
        scheduler.addAll(Arrays.asList(schedule1, schedule2));

        Schedule schedule3 = new Schedule(next);

        assertFalse(scheduler.isReservable(schedule3));

    }


}