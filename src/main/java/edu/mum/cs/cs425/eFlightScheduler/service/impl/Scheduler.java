package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import edu.mum.cs.cs425.eFlightScheduler.models.Schedule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

/**
 * Reservation class
 */
public class Scheduler {
    private Schedule rootSchedule;
    private long timeGap;

    public Scheduler(Duration timeGap) {
        this.timeGap = timeGap.getSeconds();
    }

    /**
     * Assumes all schedules are valid
     * @param collection {@link Collection} of {@link Schedule}s
     */
    public void addAll(Collection<? extends Schedule> collection) {
        collection.forEach(this::add);
    }

    public void add(Schedule schedule) {
        if (rootSchedule == null) {
            rootSchedule = schedule;
            return;
        }
        addHelper(rootSchedule, schedule);
    }

    private Schedule addHelper(Schedule scheduled, Schedule incoming) {
        if (scheduled == null) {
            scheduled = incoming;
            return scheduled;
        }
        long incomingEpochSecond = incoming.getTime().toEpochSecond(ZoneOffset.UTC);
        long scheduledEpochSecond = scheduled.getTime().toEpochSecond(ZoneOffset.UTC);
        if (incomingEpochSecond > scheduledEpochSecond)
            scheduled.after = addHelper(scheduled.after, incoming);
        else
            scheduled.before = addHelper(scheduled.before, incoming);
        return scheduled;
    }

    public boolean isReservable(Schedule schedule) {
        if (rootSchedule == null) return true;
        return reserveHelper(rootSchedule, schedule);
    }

    private boolean reserveHelper(Schedule scheduled, Schedule incoming) {
        long incomingEpochSecond = incoming.getTime().toEpochSecond(ZoneOffset.UTC);
        long scheduledEpochSecond = scheduled.getTime().toEpochSecond(ZoneOffset.UTC);
        boolean isValid = Math.abs(incomingEpochSecond - scheduledEpochSecond) >= timeGap;
        if (!isValid) return false;

        boolean isReservedAfter = incomingEpochSecond > scheduledEpochSecond;

        if (isReservedAfter) {
            if (scheduled.after == null) {
                scheduled.after = incoming;
                return true;
            }
        } else {
            if (scheduled.before == null) {
                scheduled.before = incoming;
                return true;
            }
        }

        return reserveHelper(isReservedAfter ? scheduled.after : scheduled.before, incoming);
    }

    /**
     * Print reservation
     */
    public void print() {
        printHelper(rootSchedule);
    }

    /**
     * Print Helper
     * @param schedule {@link Schedule} to resolve
     */
    private void printHelper(Schedule schedule) {
        if (schedule == null) return;
        printHelper(schedule.before);
        System.out.println(schedule);
        printHelper(schedule.after);
    }

    /**
     * TODO Get a better algorithm that searches for 'unreserved' time in between schedules
     * @return {@link LocalDateTime} instance
     */
    public LocalDateTime getSoonestTime() {
        if (rootSchedule == null) return LocalDateTime.now().plus(3, ChronoUnit.SECONDS);
        return getLastSchedule(rootSchedule).getTime();
    }

    private Schedule getLastSchedule(Schedule schedule) {
        if (schedule.after == null) return schedule;
        return getLastSchedule(schedule.after);
    }

    public static Scheduler fromSchedules(Collection<? extends Schedule> collection) {
        Scheduler scheduler = new Scheduler(Duration.of(3, ChronoUnit.SECONDS));
        scheduler.addAll(collection);
        return scheduler;
    }
}
