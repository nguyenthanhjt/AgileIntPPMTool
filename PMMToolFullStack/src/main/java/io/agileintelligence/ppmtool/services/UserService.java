package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.exceptions.ApplicationCheckedException;
import io.agileintelligence.ppmtool.exceptions.UserNameAlreadyExistedException;
import io.agileintelligence.ppmtool.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
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
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            // Username has to be unique
            newUser.setUsername(newUser.getUsername());

            //User checkUser = userRepository.findByUsername(newUser.getUsername());
            //if (null != checkUser) throw new ApplicationCheckedException("Username is already existed!");

            // Make sure password and confirm password are matched
            // Don't persist or show the confirmation password
            return userRepository.save(newUser);

        } catch (ConstraintViolationException ex) {
            throw new UserNameAlreadyExistedException("Username '" + newUser.getUsername() + "' is already existed!");
        } catch (Exception e) {
            if (e.getCause().getCause().getMessage().contains("Duplicate entry '" + newUser.getUsername() + "'")) {
                throw new UserNameAlreadyExistedException("Username '" + newUser.getUsername() + "' is already existed!");
            }
            throw new ApplicationCheckedException("System error");
        }
    }
}
