package edu.mum.cs.cs425.eFlightScheduler.service;

import java.util.List;

import edu.mum.cs.cs425.eFlightScheduler.models.Runway;

public interface IRunwayService {
	List<Runway> getAllRunways();
	Runway addNewRunway(Runway runway);
	void deleteRunway(Integer runwayId);
}
