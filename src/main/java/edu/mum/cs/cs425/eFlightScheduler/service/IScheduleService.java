package edu.mum.cs.cs425.eFlightScheduler.service;

import edu.mum.cs.cs425.eFlightScheduler.models.Schedule;
import edu.mum.cs.cs425.eFlightScheduler.models.ScheduleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Schedule service
 */
public interface IScheduleService {

    List<Schedule> getAll();

    List<Schedule> getAllFrom(Long cursor);

    Optional<Schedule> save(ScheduleDTO scheduleDTO);

}
