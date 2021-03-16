package com.makeamiracle.api.repository;

import com.makeamiracle.api.model.ERole;
import com.makeamiracle.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(ERole name);
}
