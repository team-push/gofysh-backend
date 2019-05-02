package com.gofysh.gofyshbackend.user.controller;

import com.gofysh.gofyshbackend.user.model.UserVO;
import com.gofysh.gofyshbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    UserVO getUser(@PathVariable String username) {
        return userService.getByName(username);
    }
}
