package com.uguz.flightsearch.business.service;

import com.uguz.flightsearch.entity.User;

public interface UserService {
    User getOneUserByEmail(String email);
    void add(User user);
}