package com.testpost.springbootpost.controller;

import com.testpost.springbootpost.model.Post;
import com.testpost.springbootpost.model.User;
import com.testpost.springbootpost.model.request.PostCreationRequestDto;
import com.testpost.springbootpost.model.request.UserCreationRequestDto;
import com.testpost.springbootpost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/board")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/user")//유저 생성
    public ResponseEntity<User> createUser(@RequestBody UserCreationRequestDto request){
        return ResponseEntity.ok(postService.createUser(request));
    }

    @PostMapping("/post")//글 등록
    public ResponseEntity<Post> createPost(@RequestBody PostCreationRequestDto request){
        return ResponseEntity.ok(postService.createPost(request));
    }

    @GetMapping("/post")//모든 글 가져오기
    public ResponseEntity listPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/post/{postId}")//글 아이디를 경로에 추가
    public ResponseEntity<Post> getPost(@PathVariable Long postId){//글 아이디를 받는다
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @DeleteMapping("/post/{postId}")//글 아이디를 경로에 추가
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);//서비스의 글 삭제 메소드 호출
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/post/{postId}")//글 아이디를 경로에 추가
    public ResponseEntity<Post> updatePost(@RequestBody PostCreationRequestDto request, @PathVariable Long postId){//요청 바디에 dto 담는다
        return ResponseEntity.ok(postService.updatePost(postId, request));//글 아이디와 dto 함께 전달
    }

    @GetMapping("/post/category")//카테고리 조회
   public ResponseEntity getPostCategory(){
        return ResponseEntity.ok(postService.getPostCategory());//서비스의 카테고리 조회 메서드 호출
    }
}
