package com.testpost.springbootpost.service;

import com.testpost.springbootpost.model.Post;
import com.testpost.springbootpost.model.User;
import com.testpost.springbootpost.model.request.PostCreationRequest;
import com.testpost.springbootpost.model.request.UserCreationRequest;
import com.testpost.springbootpost.repository.PostRepository;
import com.testpost.springbootpost.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public User createUser(UserCreationRequest request){//유저 생성
        User user = new User();
        BeanUtils.copyProperties(request,user);
        return userRepository.save(user);
    }

    public Post createPost(PostCreationRequest request){//글 생성
        Optional<User> user=userRepository.findById(request.getUserId());//존재하는 유저인지 확인
        if(!user.isPresent()){//존재하지 않는다면
            throw new EntityNotFoundException("user not found");
        }
        Post post = new Post();//post객체 생성
        BeanUtils.copyProperties(request,post);//request 객체를 post객체에 카피
        post.setUser(user.get());//post 객체에 얻어온 유저를 세팅
       return postRepository.save(post);
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }//모든 글 조회

    public Post getPost(Long id){
        Optional<Post> post=postRepository.findById(id);//글의 아이디로 조회
        if(post.isPresent()){
            return post.get();
        }
        throw new EntityNotFoundException("post not found under given ID");
    }

    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    public Post updatePost(Long id, PostCreationRequest request){
        Optional<Post> optionalPost=postRepository.findById(id);
        if(!optionalPost.isPresent()){
            throw new EntityNotFoundException("post not found under given ID");
        }

        Post post=optionalPost.get();
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setCreateDate(request.getCreateDate());
        return postRepository.save(post);
    }

    public List<String> getPostCategory(){
        List<Post> posts=postRepository.findAll();
        List<String> category=new ArrayList<>();
        for(Post post:posts){
            category.add(post.getCategory());
        }
        return category;
    }

}
