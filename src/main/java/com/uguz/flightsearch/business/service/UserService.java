package com.uguz.flightsearch.business.service;

import com.uguz.flightsearch.entity.User;

public interface UserService {
//    void create(User user);
//    User findByEmail(String email);
    User getOneUserByEmail(String email);
    void add(User user);
//    List<User> getAll();
}