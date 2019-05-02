package com.gofysh.gofyshbackend.message.service;

import com.gofysh.gofyshbackend.message.entity.PushMessage;

import java.util.List;

public interface PushMessageService {
    List<PushMessage> getAndReadPushMessages(Long userId);
    void send(String from, String to, String message);
}
