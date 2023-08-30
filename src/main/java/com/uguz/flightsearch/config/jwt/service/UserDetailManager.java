package com.uguz.flightsearch.config.jwt.service;

import com.uguz.flightsearch.config.jwt.config.JwtUserDetails;
import com.uguz.flightsearch.entities.User;
import com.uguz.flightsearch.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailManager implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailManager(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);

        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user=this.userRepository.findById(id).get();

        return JwtUserDetails.create(user);

    }
}
