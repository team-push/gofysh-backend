package com.gofysh.gofyshbackend.user.service.impl;

import com.gofysh.gofyshbackend.repository.UserRepository;
import com.gofysh.gofyshbackend.user.entity.User;
import com.gofysh.gofyshbackend.user.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User entity = userRepository.findByUsername(username);
        return UserPrincipal.create(entity);
    }
}
