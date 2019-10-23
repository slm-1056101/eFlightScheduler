package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import edu.mum.cs.cs425.eFlightScheduler.models.*;
import edu.mum.cs.cs425.eFlightScheduler.repository.ScheduleRepository;
import edu.mum.cs.cs425.eFlightScheduler.service.IFlightService;
import edu.mum.cs.cs425.eFlightScheduler.service.IRunwayService;
import edu.mum.cs.cs425.eFlightScheduler.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Schedule service
 */
@Service
public class ScheduleService implements IScheduleService {

    private ScheduleRepository repository;
    private IFlightService flightService;
    private IRunwayService runwayService;

    @Autowired
    public ScheduleService(ScheduleRepository repository, IFlightService flightService, IRunwayService runwayService) {
        this.repository = repository;
        this.flightService = flightService;
        this.runwayService = runwayService;
    }

    @Override
    public List<Schedule> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Schedule> getAllFrom(Long cursor) {
        return repository.findAllByIdAfter(cursor);
    }

    @Override
    public Optional<Schedule> save(ScheduleDTO scheduleDTO) {
        Optional<Status> status = getStatus(scheduleDTO.getStatus().toLowerCase());
        Optional<LocalDateTime> time = getTime(scheduleDTO.getTime());
        Optional<Flight> flight = flightService.findById(scheduleDTO.getFlightId());
        List<Runway> runways = runwayService.getAllRunways();

        if (!status.isPresent() || !time.isPresent() || !flight.isPresent() || runways.isEmpty())
            return Optional.empty();

        Schedule schedule = schedule(flight.get(), status.get(), time.get(), runways);
        schedule = repository.save(schedule);
        return Optional.of(schedule);
    }

    /**
     * Get status
     * @param status Status string
     * @return {@link Optional}
     */
    private Optional<Status> getStatus(String status) {
        try {
            return Optional.of(Status.valueOf(status));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Get status
     * @param time Time as a string
     * @return {@link Optional}
     */
    private Optional<LocalDateTime> getTime(String time) {
        try {
            return Optional.of(LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Schedule flight
     * @param flight  {@link Flight} to schedule
     * @param status  {@link Status} flight status
     * @param time    {@link LocalDateTime} flight time
     * @param runways {@link Iterable} of {@link Runway}s
     * @return {@link Schedule} a flight schedule
     */
    public Schedule schedule(Flight flight, Status status, LocalDateTime time, Iterable<Runway> runways) {
        List<RunwayTimePair> list = new ArrayList<>();
        for (Runway runway : runways) {
            List<Schedule> schedules = repository.findAllByRunwayOrderByTimeDesc(runway);
            Scheduler scheduler = Scheduler.fromSchedules(schedules);
            boolean isReservable = scheduler.isReservable(new Schedule(time));
            list.add(new RunwayTimePair(runway, isReservable ? time : scheduler.getSoonestTime()));
        }

        int randomIndex = new Random(System.currentTimeMillis()).nextInt(list.size());
        RunwayTimePair pair = list.get(randomIndex);
        return new Schedule(flight, pair.getRunway(), status, pair.getTime());
    }

}
