package com.testpost.springbootpost.model.request;

import lombok.Data;

@Data
public class UserCreationRequestDto {

    private String email;
    private String type;

}
