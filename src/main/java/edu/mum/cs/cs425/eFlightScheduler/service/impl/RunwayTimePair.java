package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import edu.mum.cs.cs425.eFlightScheduler.models.Runway;

import java.time.LocalDateTime;

/**
 * Runway time pair
 */
public class RunwayTimePair implements Comparable<RunwayTimePair> {

    private final Runway runway;
    private final LocalDateTime time;

    public RunwayTimePair(Runway runway, LocalDateTime time) {
        this.runway = runway;
        this.time = time;
    }

    @Override
    public int compareTo(RunwayTimePair o) {
        return time.compareTo(o.time);
    }

    public Runway getRunway() {
        return runway;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
