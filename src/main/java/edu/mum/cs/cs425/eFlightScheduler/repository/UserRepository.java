package edu.mum.cs.cs425.eFlightScheduler.repository;

import edu.mum.cs.cs425.eFlightScheduler.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
