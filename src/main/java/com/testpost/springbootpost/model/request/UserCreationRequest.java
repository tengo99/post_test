package com.testpost.springbootpost.model.request;

import lombok.Data;

@Data
public class UserCreationRequest {

    private String email;
    private String type;

}
