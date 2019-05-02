package com.gofysh.gofyshbackend.message.service.impl;

import com.gofysh.gofyshbackend.message.entity.PushMessage;
import com.gofysh.gofyshbackend.message.repository.PushMessageRepository;
import com.gofysh.gofyshbackend.message.service.PushMessageService;
import com.gofysh.gofyshbackend.repository.UserRepository;
import com.gofysh.gofyshbackend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private PushMessageRepository pushMessageRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<PushMessage> getAndReadPushMessages(Long userId) {
        List<PushMessage> unreadMessages = pushMessageRepository.findAllUnreadByUserId(userId);
        unreadMessages.forEach(message -> {
            message.setRead(true);
            pushMessageRepository.save(message);
        });
        return unreadMessages;
    }

    @Override
    public void send(String from, String to, String message) {
        User userFrom = userRepository.findByUsername(from);
        User userTo = userRepository.findByUsername(to);

        PushMessage newMessage = PushMessage
                .builder()
                .message(message)
                .createdAt(new Date())
                .read(false)
                .user(userTo)
                .userFrom(userFrom)
                .build();
        pushMessageRepository.save(newMessage);
    }
}
