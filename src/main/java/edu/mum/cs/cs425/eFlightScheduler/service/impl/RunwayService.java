package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.eFlightScheduler.models.Runway;
import edu.mum.cs.cs425.eFlightScheduler.repository.IRunwayRepository;
import edu.mum.cs.cs425.eFlightScheduler.service.IRunwayService;

@Service
public class RunwayService implements IRunwayService{

	@Autowired
	private IRunwayRepository runwayRepository;

	@Override
	public List<Runway> getAllRunways() {
		return runwayRepository.findAll(Sort.by("id"));
	}

	@Override
	public Runway addNewRunway(Runway runway) {
		return runwayRepository.save(runway);
	}

	@Override
	public void deleteRunway(Integer runwayId) {
		runwayRepository.deleteById(runwayId);
		
	}
	
	
}
