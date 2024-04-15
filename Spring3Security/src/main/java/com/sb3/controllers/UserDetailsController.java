package com.sb3.controllers;

import com.sb3.beans.UserDetailBean;
import com.sb3.repository.UserDetailsRepository;
import com.sb3.service.UserDetailsService;
import com.sb3.utils.JwtUtils;
import com.sb3.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDetailsController {

//    private final JwtUtils jwtUtil;
//
//    private final UserUtils userUtils;

    private final UserDetailsService userDetailsService;

//    private final UserDetailsRepository userDetailsRepository;

    @GetMapping("/details")
    public ResponseEntity<UserDetailBean> getUserDetails() {
        return ResponseEntity.ok(userDetailsService.getUserDetails());
    }

//    private String getEmailFromToken(String token) {
//        if (token != null && token.startsWith("Bearer ")) {
//            String jwtToken = token.substring(7);
//            return jwtUtil.extractEmail(jwtToken);
//        }
//        return null;
//    }

}
