package com.gofysh.gofyshbackend.user.service.impl;

import com.gofysh.gofyshbackend.auth.model.Registration;
import com.gofysh.gofyshbackend.exception.UserAlreadyExistingException;
import com.gofysh.gofyshbackend.repository.PrivilegeRepository;
import com.gofysh.gofyshbackend.repository.RoleRepository;
import com.gofysh.gofyshbackend.repository.UserRepository;
import com.gofysh.gofyshbackend.user.entity.Privilege;
import com.gofysh.gofyshbackend.user.entity.Role;
import com.gofysh.gofyshbackend.user.entity.User;
import com.gofysh.gofyshbackend.user.model.UserVO;
import com.gofysh.gofyshbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("goFyshUserService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PrivilegeRepository privilegeRepository;

    @Override
    public UserVO register(Registration registration) throws UserAlreadyExistingException {
        if(userRepository.countByUsername(registration.getUsername()) > 0) {
            throw new UserAlreadyExistingException("user already existing");
        }

        User newUser = User.builder()
                .fullName(registration.getFullName())
                .username(registration.getUsername())
                .password(passwordEncoder.encode(registration.getPassword()))
                .build();
        return userRepository.save(newUser).toVO();
    }

    @Override
    public UserVO getByName(String username) {
        return userRepository.findByUsername(username).toVO();
    }
}
