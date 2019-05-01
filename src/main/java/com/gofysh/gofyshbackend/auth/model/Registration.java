package com.gofysh.gofyshbackend.auth.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    private String username;
    private String password;
    private String fullName;
}
