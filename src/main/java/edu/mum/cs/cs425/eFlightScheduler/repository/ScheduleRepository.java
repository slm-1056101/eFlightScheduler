package edu.mum.cs.cs425.eFlightScheduler.repository;

import edu.mum.cs.cs425.eFlightScheduler.models.Runway;
import edu.mum.cs.cs425.eFlightScheduler.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Schedule repository
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByIdAfter(Long cursor);

    List<Schedule> findAllByRunwayOrderByTimeDesc(Runway runway);

}
