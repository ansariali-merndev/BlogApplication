package com.ali.blog.services.impl;

import com.ali.blog.dtos.AllPostDto;
import com.ali.blog.dtos.CommonDto;
import com.ali.blog.dtos.PostRequestDto;
import com.ali.blog.dtos.PostResponseDto;
import com.ali.blog.entities.Post;
import com.ali.blog.mappers.PostMapper;
import com.ali.blog.repositories.PostRepository;
import com.ali.blog.services.HelperService;
import com.ali.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final HelperService helperService;

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post userPost = helperService.createHelperPost(postRequestDto);
        Post savedPost = postRepository.save(userPost);
        return PostMapper.PostEntityToPostResponse(savedPost.getId());
    }

    @Override
    public List<AllPostDto> getPost() {
        List<AllPostDto> dtos = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            dtos.add(PostMapper.toAllPostDto(post));
        }
            return dtos;
    }

    @Override
    public CommonDto deletePostById(Long postId) {
        postRepository.deleteById(postId);
        return CommonDto
                .builder()
                .success(true)
                .message("Post Deleted Successfully")
                .build();
    }


}
