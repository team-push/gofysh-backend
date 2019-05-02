package com.gofysh.gofyshbackend.user.entity;

import com.gofysh.gofyshbackend.message.entity.PushMessage;
import com.gofysh.gofyshbackend.user.model.UserVO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Table(name="user")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public UserVO toVO() {
        return UserVO.builder()
                .fullName(fullName)
                .username(username)
                .id(id)
                .build();
    }
}
