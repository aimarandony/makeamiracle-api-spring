package com.makeamiracle.api;

import com.makeamiracle.api.model.ERole;
import com.makeamiracle.api.model.Role;
import com.makeamiracle.api.model.User;
import com.makeamiracle.api.service.RoleService;
import com.makeamiracle.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role role1 = new Role();
        role1.setName(ERole.ROLE_ADMIN);
        role1 = roleService.create(role1);

        Role role2 = new Role();
        role2.setName(ERole.ROLE_USER);
        role2 = roleService.create(role2);

        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("123admin");
        user1.setRoles(Collections.singletonList(role1));
        userService.signUp(user1);

        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword("123user");
        user2.setRoles(Collections.singletonList(role2));
        userService.signUp(user2);
    }
}
