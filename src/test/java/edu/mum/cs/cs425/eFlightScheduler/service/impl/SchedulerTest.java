package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import edu.mum.cs.cs425.eFlightScheduler.models.Schedule;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchedulerTest {

    private Scheduler scheduler = new Scheduler(Duration.of(3, ChronoUnit.SECONDS));

    @Test
    public void testSchedulingIsSuccessfulOnRunway() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.plus(5, ChronoUnit.SECONDS);

        Schedule schedule1 = new Schedule(now);
        Schedule schedule2 = new Schedule(next);

        boolean reservable1 = scheduler.isReservable(schedule1);
        assertTrue(reservable1);

        boolean reservable2 = scheduler.isReservable(schedule2);
        assertTrue(reservable2);

    }

    @Test
    public void testSchedulingFails() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.plus(2, ChronoUnit.SECONDS);

        Schedule schedule1 = new Schedule(now);
        Schedule schedule2 = new Schedule(next);
        Schedule schedule3 = new Schedule(next);

        scheduler.addAll(Arrays.asList(schedule1, schedule2, schedule3));
//        boolean reservable1 = scheduler.isReservable(schedule1);
//        assertTrue(reservable1);
//
//        boolean reservable2 = scheduler.isReservable(schedule2);
//        scheduler.isReservable(schedule2);
//        assertFalse(reservable2);
//
        scheduler.print();

    }


}