package com.makeamiracle.api.service;

import com.makeamiracle.api.exception.BadRequest;
import com.makeamiracle.api.model.ERole;
import com.makeamiracle.api.model.User;
import com.makeamiracle.api.repository.RoleRepository;
import com.makeamiracle.api.repository.UserRepository;
import com.makeamiracle.api.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    public String signIn(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, repository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new BadRequest("Invalid username/password supplied.");
        }
    }

    public String signUp(User user) {
        if (repository.existsByUsername(user.getUsername()))
            throw new BadRequest("Username is already in use");

        if (user.getRoles() == null)
            user.setRoles(Collections.singletonList(roleRepository.findByName(ERole.ROLE_USER)));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }

    public User whoAmi(HttpServletRequest request) {
        return repository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, repository.findByUsername(username).getRoles());
    }

    public User findByUsername(String username){
        return repository.findByUsername(username);
    }
}
