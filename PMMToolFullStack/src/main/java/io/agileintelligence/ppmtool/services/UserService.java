package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User saveUser(User newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        // Username has to be unique
        // Make sure password and confirm password are matched
        // Don't persist or show the confirmation password
        return userRepository.save(newUser);
    }
}
