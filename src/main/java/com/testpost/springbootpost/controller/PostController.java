package com.testpost.springbootpost.controller;

import com.testpost.springbootpost.model.Post;
import com.testpost.springbootpost.model.User;
import com.testpost.springbootpost.model.request.PostCreationRequest;
import com.testpost.springbootpost.model.request.UserCreationRequest;
import com.testpost.springbootpost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/board")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/user")//유저 생성
    public ResponseEntity<User> createUser(@RequestBody UserCreationRequest request){
        return ResponseEntity.ok(postService.createUser(request));
    }

    @PostMapping("/post")//글 등록
    public ResponseEntity<Post> createPost(@RequestBody PostCreationRequest request){
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

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/post/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody PostCreationRequest request, @PathVariable Long postId){
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }

    @GetMapping("/post/category")
   public ResponseEntity getPostCategory(){
        return ResponseEntity.ok(postService.getPostCategory());
    }
}
