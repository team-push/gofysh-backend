package com.gofysh.gofyshbackend.message.repository;

import com.gofysh.gofyshbackend.message.entity.PushMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PushMessageRepository extends JpaRepository<PushMessage, Long> {
    @Query("SELECT p FROM PushMessage p WHERE p.user.id=?1 AND p.read = false")
    List<PushMessage> findAllUnreadByUserId(Long userId);
}
