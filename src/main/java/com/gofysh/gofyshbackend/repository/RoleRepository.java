package com.gofysh.gofyshbackend.repository;

import com.gofysh.gofyshbackend.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
