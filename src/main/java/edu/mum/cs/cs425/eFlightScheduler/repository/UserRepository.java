package edu.mum.cs.cs425.eFlightScheduler.repository;

import edu.mum.cs.cs425.eFlightScheduler.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
