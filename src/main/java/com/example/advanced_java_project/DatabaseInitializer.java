package com.example.advanced_java_project;

import org.springframework.stereotype.Component;

@Component
//public class DatabaseInitializer implements CommandLineRunner {
public class DatabaseInitializer {

    //private final UserRepository userRepository;

/*    public DatabaseInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    //@Override
/*    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("secretary").isEmpty()) {
            userRepository.save(new Secretory("secretary", "1234", Roles.Secretory ));
        }

        if (userRepository.findByUsername("doctor").isEmpty()) {
            userRepository.save(new Doctor("doctor", "abcd", Roles.Doctor));
        }
    }*/
}
