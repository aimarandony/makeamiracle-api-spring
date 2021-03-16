package com.makeamiracle.api.service;

import com.makeamiracle.api.model.Role;
import com.makeamiracle.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role create(Role role){
        return repository.save(role);
    }
}
