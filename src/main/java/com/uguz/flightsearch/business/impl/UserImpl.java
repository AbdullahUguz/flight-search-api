package com.uguz.flightsearch.business.impl;

import com.uguz.flightsearch.business.service.UserService;
import com.uguz.flightsearch.entity.User;
import com.uguz.flightsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserImpl(UserRepository userRepository) {
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
