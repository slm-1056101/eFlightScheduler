package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import edu.mum.cs.cs425.eFlightScheduler.models.*;
import edu.mum.cs.cs425.eFlightScheduler.repository.ScheduleRepository;
import edu.mum.cs.cs425.eFlightScheduler.service.IFlightService;
import edu.mum.cs.cs425.eFlightScheduler.service.IRunwayService;
import edu.mum.cs.cs425.eFlightScheduler.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

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
        return repository.findAllByFlightAfter(cursor);
    }

    @Override
    public Optional<Schedule> save(ScheduleDTO scheduleDTO) {
        Optional<Status> status = getStatus(scheduleDTO.getStatus().toLowerCase());
        Optional<LocalDateTime> time = getTime(scheduleDTO.getTime());
        Optional<Flight> flight = flightService.findById(scheduleDTO.getFlightId());

        if (!status.isPresent() || !time.isPresent() || !flight.isPresent())
            return Optional.empty();


        Schedule schedule = schedule(flight.get(), status.get(), time.get());

        // TODO Persist Schedule
        // repository.save(schedule);

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
            return Optional.of(LocalDateTime.parse(time));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Schedule flight
     * @param flight {@link Flight} to schedule
     * @param status {@link Status} flight status
     * @param time   {@link LocalDateTime} flight time
     * @return {@link Schedule} a flight schedule
     */
    public Schedule schedule(Flight flight, Status status, LocalDateTime time) {
        PriorityQueue<RunwayTimePair> queue = new PriorityQueue<>();
        List<Runway> runways = runwayService.getAllRunways();
        for (Runway runway : runways) {
            List<Schedule> schedules = repository.findFirst3ByRunwayOrderByTimeDesc(runway);
            Scheduler scheduler = Scheduler.getTypicalScheduler();
            scheduler.addAll(schedules);
            Schedule schedule = new Schedule(time);
            boolean isReservable = scheduler.isReservable(schedule);
            queue.offer(new RunwayTimePair(runway, isReservable ? time : scheduler.getSoonestTime()));
        }

        RunwayTimePair pair = queue.peek();
        assert pair != null;
        return new Schedule(flight, pair.getRunway(), status, pair.getTime());
    }

}
