package com.gofysh.gofyshbackend.message.entity;

import com.gofysh.gofyshbackend.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="push_message")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    @ManyToOne
    @JoinColumn(name="user_from_id")
    private User userFrom;
    @ManyToOne
    @JoinColumn(name="user_to_id")
    private User user;
    private boolean read;
    private Date createdAt;
}

