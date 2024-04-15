package com.sb3.service.impl;

import com.sb3.beans.UserDetailBean;
import com.sb3.entities.UserDetailsEntity;
import com.sb3.repository.UserDetailsRepository;
import com.sb3.service.UserDetailsService;
import com.sb3.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserUtils userUtils;

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailBean getUserDetails() {
        try{
            String email = userUtils.getLoggedInUserEmail();
            log.info("Get User details : {}", email);

            UserDetailsEntity userDetails = userDetailsRepository.findByEmail(email);
            if(Objects.nonNull(userDetails)){
                return UserDetailBean.builder()
                        .userId(userDetails.getUniqueId())
                        .email(userDetails.getEmail())
                        .build();
            }
        }catch (Exception e){
            log.error("Exception in getUserDetails : {}", e.getMessage());
        }
        return null;
    }
}
