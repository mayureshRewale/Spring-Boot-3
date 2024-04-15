package com.sb3.service.impl;

import com.sb3.beans.LoginRequestBean;
import com.sb3.beans.LoginResponseBean;
import com.sb3.beans.ServiceResponseBean;
import com.sb3.beans.UserDetailBean;
import com.sb3.entities.UserDetailsEntity;
import com.sb3.enums.UserRolesEnum;
import com.sb3.repository.RolesRepository;
import com.sb3.repository.UserDetailsRepository;
import com.sb3.service.AuthenticationService;
import com.sb3.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsRepository userDetailsRepository;

    private final RolesRepository rolesRepository;

    private final JwtUtils jwtUtil;

    @Override
    public ServiceResponseBean registerUser(UserDetailBean userDetailBean) {
        try{
            log.info("Register User : {}", userDetailBean.getEmail());

            UserDetailsEntity user = userDetailsRepository.findByEmail(userDetailBean.getEmail());
            if(Objects.nonNull(user)){
                return ServiceResponseBean.builder()
                        .status(Boolean.FALSE)
                        .errorMessage("User Already Exists with Email " + user.getEmail())
                        .build();
            }

            user = new UserDetailsEntity();
            user.setEmail(userDetailBean.getEmail());
            user.setUniqueId(UUID.randomUUID().toString());
            user.setRoles(Set.of(rolesRepository.findByName(UserRolesEnum.USER_ROLE)));

            user = userDetailsRepository.save(user);

            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .message("User Registered Successfully")
                    .data(UserDetailBean.builder()
                            .userId(user.getUniqueId())
                            .email(user.getEmail())
                            .build())
                    .build();

        }catch (Exception e){
            log.error("exception in registerUser : {}", e.getMessage());

            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .errorMessage("Something went wrong")
                    .build();
        }
    }

    @Override
    public ServiceResponseBean loginUser(LoginRequestBean loginRequestBean) {
        try{
            log.info("Login User : {}", loginRequestBean.getEmail());

            UserDetailsEntity userDetail = userDetailsRepository.findByEmail(loginRequestBean.getEmail());

            if (Objects.nonNull(userDetail)) {
                String token = jwtUtil.generateToken(userDetail.getEmail());

                return ServiceResponseBean.builder()
                        .status(Boolean.TRUE)
                        .message("Login Successful")
                        .data(LoginResponseBean.builder()
                                .accessToken(token)
                                .userDetailBean(
                                        UserDetailBean.builder()
                                                .userId(userDetail.getUniqueId())
                                                .email(userDetail.getEmail())
                                                .build()
                                )
                                .build()
                        )
                        .build();
            } else {

                return ServiceResponseBean.builder()
                        .status(Boolean.FALSE)
                        .errorMessage("User not found with Email " + loginRequestBean.getEmail())
                        .build();
            }

        }catch (Exception e){
            log.error("Exception in loginUser : {}", e.getMessage());
            return ServiceResponseBean.builder()
                    .status(Boolean.FALSE)
                    .errorMessage("Something went wrong")
                    .build();
        }
    }
}
