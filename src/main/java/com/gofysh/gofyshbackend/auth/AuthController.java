package com.gofysh.gofyshbackend.auth;

import com.gofysh.gofyshbackend.auth.model.JwtAuthenticationResponse;
import com.gofysh.gofyshbackend.auth.model.LoginRequest;
import com.gofysh.gofyshbackend.auth.model.Registration;
import com.gofysh.gofyshbackend.auth.service.TokenService;
import com.gofysh.gofyshbackend.exception.UserAlreadyExistingException;
import com.gofysh.gofyshbackend.user.model.UserVO;
import com.gofysh.gofyshbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    private UserService goFyshUserService;

    @PostMapping("/login")
    JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenService.createToken(authentication);

        return JwtAuthenticationResponse.builder().accessToken(jwt).build();
    }

    @PostMapping("/register")
    UserVO register(@Valid @RequestBody Registration register) {
        try {
            return goFyshUserService.register(register);
        } catch (UserAlreadyExistingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
