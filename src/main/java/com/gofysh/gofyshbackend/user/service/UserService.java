package com.gofysh.gofyshbackend.user.service;

import com.gofysh.gofyshbackend.auth.model.Registration;
import com.gofysh.gofyshbackend.exception.UserAlreadyExistingException;
import com.gofysh.gofyshbackend.user.model.UserVO;

public interface UserService {
    UserVO register(Registration registration) throws UserAlreadyExistingException;
    UserVO getByName(String username);
}
