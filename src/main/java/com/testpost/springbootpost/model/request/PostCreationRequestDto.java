package com.testpost.springbootpost.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class PostCreationRequestDto {

    private String content;
    private Date createDate;
    private String category;
    private Long userId;
}
