package com.gofysh.gofyshbackend.user.model;

import com.gofysh.gofyshbackend.user.entity.Role;
import com.gofysh.gofyshbackend.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class UserPrincipal implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream().map(Role::getPrivileges)
                .flatMap(Collection::stream).map(p -> new SimpleGrantedAuthority(p.getPrivilege())).collect(Collectors.toList());

        return UserPrincipal.builder()
                .id(userEntity.getId())
                .authorities(authorities)
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
