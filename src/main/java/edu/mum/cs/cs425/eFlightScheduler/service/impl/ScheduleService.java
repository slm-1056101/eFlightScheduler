package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;
import edu.mum.cs.cs425.eFlightScheduler.models.Schedule;
import edu.mum.cs.cs425.eFlightScheduler.models.ScheduleDTO;
import edu.mum.cs.cs425.eFlightScheduler.models.Status;
import edu.mum.cs.cs425.eFlightScheduler.repository.ScheduleRepository;
import edu.mum.cs.cs425.eFlightScheduler.service.IFlightService;
import edu.mum.cs.cs425.eFlightScheduler.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

/**
 * Schedule service
 */
@Service
public class ScheduleService implements IScheduleService {

    private ScheduleRepository repository;
    private IFlightService flightService;

    @Autowired
    public ScheduleService(ScheduleRepository repository, IFlightService flightService) {
        this.repository = repository;
        this.flightService = flightService;
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
        Optional<Flight> flight = flightService.findById(scheduleDTO.getFlightId());
        if (!flight.isPresent()) return Optional.empty();

        Status status;
        LocalDateTime time;
        try {
            status = Status.valueOf(scheduleDTO.getStatus().toLowerCase());
            time = LocalDateTime.parse(scheduleDTO.getTime());
        } catch (IllegalArgumentException | DateTimeParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        Schedule schedule = schedule(flight.get(), status, time);

        // TODO Persist Schedule
        // repository.save(schedule);

        return Optional.of(schedule);
    }

    /**
     * Schedule flight
     * @param flight {@link Flight} to schedule
     * @param status {@link Status} flight status
     * @param time   {@link LocalDateTime} flight time
     * @return {@link Schedule} a flight schedule
     */
    public Schedule schedule(Flight flight, Status status, LocalDateTime time) {
        Schedule schedule = new Schedule(flight, status, time);

        // TODO Schedule flight on a particular runway

        return schedule;
    }
}
