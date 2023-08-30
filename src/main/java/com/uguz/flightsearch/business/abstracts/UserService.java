package com.uguz.flightsearch.business.abstracts;

import com.uguz.flightsearch.entities.User;

public interface UserService {
//    void create(User user);
//    User findByEmail(String email);
    User getOneUserByEmail(String email);
    void add(User user);
//    List<User> getAll();
}