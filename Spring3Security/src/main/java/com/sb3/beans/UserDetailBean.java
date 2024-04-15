package com.sb3.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailBean {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

}
