package com.sb3.controllers;

import com.sb3.beans.LoginRequestBean;
import com.sb3.beans.ServiceResponseBean;
import com.sb3.beans.UserDetailBean;
import com.sb3.entities.RolesEntity;
import com.sb3.entities.UserDetailsEntity;
import com.sb3.enums.UserRolesEnum;
import com.sb3.repository.RolesRepository;
import com.sb3.repository.UserDetailsRepository;
import com.sb3.service.AuthenticationService;
import com.sb3.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserDetailsRepository userDetailsRepository;

    private final AuthenticationService authenticationService;

    private final JwtUtils jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ServiceResponseBean> registerUser(@RequestBody UserDetailBean userDetailBean) {
        return ResponseEntity.ok(authenticationService.registerUser(userDetailBean));
    }

    @PostMapping("/login")
    public ResponseEntity<ServiceResponseBean> loginUser(@RequestBody LoginRequestBean loginRequestBean) {
        return ResponseEntity.ok(authenticationService.loginUser(loginRequestBean));
    }

}
