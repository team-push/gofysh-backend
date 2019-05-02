package com.gofysh.gofyshbackend.message.controller;

import com.gofysh.gofyshbackend.message.entity.PushMessage;
import com.gofysh.gofyshbackend.message.service.PushMessageService;
import com.gofysh.gofyshbackend.user.model.UserVO;
import com.gofysh.gofyshbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private UserService goFyshUserService;
    @Autowired
    private PushMessageService pushMessageService;

    @GetMapping()
    List<PushMessage> getUnreadMessages(Principal principal) {
        UserVO userVO = goFyshUserService.getByName(principal.getName());
        if(Objects.isNull(userVO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.");
        }
        return pushMessageService.getAndReadPushMessages(userVO.getId());
    }

    @PostMapping("/to/{username}")
    void pushMessage(@PathVariable String username, Principal principal, @RequestParam(name="message") String message) {
        System.out.println("username = " + username);
        pushMessageService.send(principal.getName(), username, message);
    }
}
