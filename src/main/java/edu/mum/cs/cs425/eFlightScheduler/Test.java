package edu.mum.cs.cs425.eFlightScheduler;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;
import edu.mum.cs.cs425.eFlightScheduler.models.Role;
import edu.mum.cs.cs425.eFlightScheduler.models.User;
import edu.mum.cs.cs425.eFlightScheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Test {

    @Autowired
    private UserRepository userRepository;

    void doMagic() {
        System.out.println("Something");

        User admin = new User("Charlo", "Dev", Role.ADMIN);
        User controller = new User("Another", "Person", Role.CONTROLLER);

        Flight dl123 = new Flight("Delta", "Flying high");
        Flight aa321 = new Flight("American Airlines", "Where do you want to go?");

        userRepository.save(admin);
        userRepository.save(controller);

        // TODO Add test flight + runway data
    }
}
