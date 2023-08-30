package com.uguz.flightsearch.business.concretes;

import com.uguz.flightsearch.business.abstracts.UserService;
import com.uguz.flightsearch.entities.User;
import com.uguz.flightsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserManager implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getOneUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void add(User user) {
        this.userRepository.save(user);
    }

}
