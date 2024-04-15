package com.sb3.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseBean {

    @JsonProperty("status")
    Boolean status;

    @JsonProperty("message")
    String message;

    @JsonProperty("data")
    Object data;

    @JsonProperty("error_message")
    String errorMessage;

}
