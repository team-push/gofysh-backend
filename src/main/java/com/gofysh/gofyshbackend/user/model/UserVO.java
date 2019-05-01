package com.gofysh.gofyshbackend.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String username;
    private String password;
    private String fullName;
}
