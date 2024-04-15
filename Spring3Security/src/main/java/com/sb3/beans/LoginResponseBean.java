package com.sb3.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseBean {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("user_details")
    private UserDetailBean userDetailBean;

}
