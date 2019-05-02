package com.gofysh.gofyshbackend.repository;

import com.gofysh.gofyshbackend.user.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
