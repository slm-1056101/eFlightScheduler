package edu.mum.cs.cs425.eFlightScheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Long>{

}
