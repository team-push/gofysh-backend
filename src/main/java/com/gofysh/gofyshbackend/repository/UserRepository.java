package com.gofysh.gofyshbackend.repository;

import com.gofysh.gofyshbackend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT COUNT(1) FROM User u WHERE u.username = ?1")
    int countByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username=?1")
    User findByUsername(String username);
}
