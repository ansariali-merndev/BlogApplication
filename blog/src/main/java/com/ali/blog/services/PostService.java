package com.ali.blog.services;

import com.ali.blog.dtos.AllPostDto;
import com.ali.blog.dtos.CommonDto;
import com.ali.blog.dtos.PostRequestDto;
import com.ali.blog.dtos.PostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto createPost (PostRequestDto postRequestDto);
    List<AllPostDto> getPost ();
    CommonDto deletePostById (Long postId);
}
