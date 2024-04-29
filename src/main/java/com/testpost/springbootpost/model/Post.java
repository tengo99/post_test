package com.testpost.springbootpost.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//게시글 아이디
    private String content;//게시글 내용
    private String image;//이미지
    private Date createDate;//작성일자
    private String category;//카테고리


    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonManagedReference
    private User user;//유저아이디(외래키)

}
