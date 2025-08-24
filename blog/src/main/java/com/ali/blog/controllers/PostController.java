package com.ali.blog.controllers;

import com.ali.blog.dtos.AllPostDto;
import com.ali.blog.dtos.CommonDto;
import com.ali.blog.dtos.PostRequestDto;
import com.ali.blog.dtos.PostResponseDto;
import com.ali.blog.services.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostServiceImpl postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost (@RequestBody PostRequestDto postRequestDto) {
        try {
            PostResponseDto postResponseDto = postService.createPost(postRequestDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(postResponseDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(PostResponseDto.builder().success(false).message(e.getMessage()).build());
        }
    }

    @GetMapping
    public List<AllPostDto> getAllPost  () {
        return postService.getPost();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonDto> deletePost (@PathVariable Long id) {
        try {
            CommonDto commonDto = postService.deletePostById(id);
            return ResponseEntity.ok(commonDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CommonDto.builder().success(false).message(e.getMessage()).build());
        }
    }
}
