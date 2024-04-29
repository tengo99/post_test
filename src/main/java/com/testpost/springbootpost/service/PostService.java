package com.testpost.springbootpost.service;

import com.testpost.springbootpost.model.Post;
import com.testpost.springbootpost.model.User;
import com.testpost.springbootpost.model.request.PostCreationRequestDto;
import com.testpost.springbootpost.model.request.UserCreationRequestDto;
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


    public User createUser(UserCreationRequestDto request){//유저 생성
        User user = new User();//유저 객체 생성해서
        BeanUtils.copyProperties(request,user);//dto에 있는 프로퍼티 값들을 복사한다
        return userRepository.save(user);//save()에 user넣어준다.
    }

    public Post createPost(PostCreationRequestDto request){//글 생성
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
        if(post.isPresent()){//존재한다면
            return post.get();//가져온다
        }
        throw new EntityNotFoundException("post not found under given ID");//존재하지 않는다면
    }

    public void deletePost(Long id){//아이디 전달받아서
        postRepository.deleteById(id);//아이디로 삭제
    }

    public Post updatePost(Long id, PostCreationRequestDto request){//아이디와 dto전달받아서
        Optional<Post> optionalPost=postRepository.findById(id);//아이디로 해당 글이 있는지 확인
        if(!optionalPost.isPresent()){//존재하지 않는다면
            throw new EntityNotFoundException("post not found under given ID");//에러 발생
        }
        //존재한다면
        Post post=optionalPost.get();//글 객체를 얻어와서
        post.setContent(request.getContent());//요청 dto에 담겨있는 content(수정한 글 내용)로 set한다  
        post.setCategory(request.getCategory());//요청 dto에 담겨있는 category(수정한 카테고리)로 set한다
        post.setCreateDate(request.getCreateDate());//요청 dto에 담겨있는 createDate(수정한 작성일자)로 set한다
        return postRepository.save(post);//수정 후의 글을 save()한다
    }

    public List<String> getPostCategory(){//카테고리 조회하는 메서드
        List<Post> posts=postRepository.findAll();//모든 글을 가져온다
        List<String> category=new ArrayList<>();//category 리스트를 생성한다(각 post들의 category를 담을 리스트입니다)
        for(Post post:posts){
            category.add(post.getCategory());//category 리스트에 각각의 글들의 카테고리를 담아준다
        }
        return category;//카테고리 리스트를 리턴합니다
    }

}
