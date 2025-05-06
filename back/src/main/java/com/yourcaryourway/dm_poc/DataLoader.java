package com.yourcaryourway.dm_poc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.yourcaryourway.dm_poc.model.User;
import com.yourcaryourway.dm_poc.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si les utilisateurs existent, sinon les créer
        if (userRepository.findByUsername("AliceMargatroid").isEmpty()) {
            User aliceMargatroid = new User();
            aliceMargatroid.setUsername("AliceMargatroid");
            userRepository.save(aliceMargatroid);
        }
        
        if (userRepository.findByUsername("ThomasFayac").isEmpty()) {
            User thomasFayac = new User();
            thomasFayac.setUsername("ThomasFayac");
            userRepository.save(thomasFayac);
        }
    }

}
