package com.makeamiracle.api.controller;

import com.makeamiracle.api.model.User;
import com.makeamiracle.api.payload.request.LoginRequest;
import com.makeamiracle.api.payload.response.LoginResponse;
import com.makeamiracle.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    public LoginResponse login(@RequestBody LoginRequest user) {
        String token = service.signIn(user.getUsername(), user.getPassword());
        User userAuthenticated = service.findByUsername(user.getUsername());
        LoginResponse response = new LoginResponse();
        response.setId(userAuthenticated.getId());
        response.setUsername(userAuthenticated.getUsername());
        response.setRoles(userAuthenticated.getRoles());
        response.setToken(token);
        return response;
    }
}
