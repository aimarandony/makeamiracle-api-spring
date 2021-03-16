package com.makeamiracle.api.payload.response;

import com.makeamiracle.api.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private Long id;
    private String username;
    private List<Role> roles;
    private final String type = "Bearer";
    private String token;
}
